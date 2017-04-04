package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.UserLoginModel;
import com.crud.service.IUserLogin;

@RestController
public class UserLoginAuthenticationController {
	
	@Autowired
	IUserLogin iUserLogin;

	@RequestMapping(value= "/login", method = RequestMethod.GET)
	public boolean login(@RequestParam("emailid") String emailid,@RequestParam("password") String password ){
		
		UserLoginModel userLoginModel = new UserLoginModel();
		userLoginModel.setEmailid(emailid);
		userLoginModel.setPassword(password);
		
		boolean re = iUserLogin.checkUser(userLoginModel);
		
		return re;
		
	}
}
