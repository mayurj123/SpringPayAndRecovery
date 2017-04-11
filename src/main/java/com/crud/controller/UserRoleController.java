package com.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.RegisterUserModel;
import com.crud.bean.UserRoleModel;
import com.crud.service.IUserRoleService;

@RestController
public class UserRoleController {

	@Autowired
	IUserRoleService iUserRoleService;

	@RequestMapping(value = "/userRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Test
	public @ResponseBody String checkUserRoles(HttpServletRequest request) {

		UserRoleModel userRoleModel = new UserRoleModel();
		String userLoggedIn = null;
		HttpSession session = request.getSession(false);
		try {
			userLoggedIn = (String) session.getAttribute("emailid");
			userRoleModel.setUserLoggedIn(userLoggedIn);
		} catch (NullPointerException e) {
			System.out.println("Please Login First to maintain Sesstion " + e);
			return "Please Login First to maintain Sesstion ";
		}
		userRoleModel = iUserRoleService.checkUserRole(userRoleModel);

		String userRole = null;
		try {
			if (userRoleModel.getRole().contains("admin")) {
				userRole = userRoleModel.getRole();

			} else if (userRoleModel.getRole().contains("tpclaim")) {
				userRole = userRoleModel.getRole();

			} else if (userRoleModel.getRole().contains("verticalhead")) {
				userRole = userRoleModel.getRole();
				System.out.println("Role verticalhead");
			} else if (userRoleModel.getRole().contains("vendor")) {
				userRole = userRoleModel.getRole();
			}
			return "User Role is " + userRole;
		} catch (Exception e) {
			System.out.println("Exception Occured in user role Controller");
			return "Exception Occured in user role Controller";
		}
		
	}
}
