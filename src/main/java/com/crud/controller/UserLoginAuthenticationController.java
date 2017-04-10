package com.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.crud.bean.UserLoginModel;
import com.crud.service.IUserLogin;

@RestController
@SessionAttributes("emailid")
public class UserLoginAuthenticationController {
	private static Logger logger = LoggerFactory.getLogger(UserLoginAuthenticationController.class);

	@Autowired
	IUserLogin iUserLogin;

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String loginHandler(HttpServletRequest request, @RequestParam("emailid") String emailid,
			@RequestParam("password") String password) {

		UserLoginModel userLoginModel = new UserLoginModel();
		userLoginModel.setEmailid(emailid);
		userLoginModel.setPassword(password);
		boolean re = iUserLogin.checkUser(userLoginModel);
		if (re) {
			HttpSession session = request.getSession();
			String activeSession = (String) session.getAttribute("emailid");
			if (activeSession == null) {
				session.setAttribute("emailid", request.getParameter("emailid"));
			} else {
				return "User Already Logged In, Please logout first!";
			}
			logger.info("User Logged In Sucessfully");
			return "User Logged In";
		} else {
			return "Incorrect User Name or Password";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String logoutHandler(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			return "User Successfully Logged Out !";
		} else {
			logger.info("Please Login First!");
			return "No Session is Active Please Login First!";
		}
	}
}
