package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.RegisterUserModel;
import com.crud.service.IRegisterUser;

@RestController
public class RegisterUserController {
	
	@Autowired
	IRegisterUser iRegisterUser;
	
	@RequestMapping(value="/registeruser", method= RequestMethod.POST)
	public @ResponseBody String userreg(@RequestParam("fname")String fname,@RequestParam("lname")String lname,
										@RequestParam("role")String role,@RequestParam("emailid")String emailid,
										@RequestParam("password")String password) throws Exception{
		
		RegisterUserModel registerusermodel=new RegisterUserModel();
		
		registerusermodel.setFname(fname);
		registerusermodel.setLname(lname);
		registerusermodel.setRole(role);
		registerusermodel.setEmailid(emailid);
		registerusermodel.setPassword(password);
		
		
		boolean re=iRegisterUser.registeruser(registerusermodel);
		if(re){
			return "User registered Successfully";
		}else{
			return "Email Id Already Exists, Please try Another Email Id";
		}
		
		
	}

}
