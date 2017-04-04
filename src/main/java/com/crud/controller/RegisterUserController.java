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
	public @ResponseBody String userreg(@RequestParam("userid")String userid,@RequestParam("fname")String fname,
										@RequestParam("lname")String lname,@RequestParam("emailid")String emailid) throws Exception{
		
		RegisterUserModel registerusermodel=new RegisterUserModel();
		registerusermodel.setUserid(userid);
		registerusermodel.setFname(fname);
		registerusermodel.setLname(lname);
		registerusermodel.setEmailid(emailid);
		
		iRegisterUser.registeruser(registerusermodel);
		
		return "In controller user registered";
		
	}

}
