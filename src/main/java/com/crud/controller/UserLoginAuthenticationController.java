package com.crud.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crud.bean.UserLoginModel;
import com.crud.service.IUserLogin;

@RestController
public class UserLoginAuthenticationController {

	@Autowired
	IUserLogin iUserLogin;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public boolean loginHandler(@RequestParam("emailid") String emailid, @RequestParam("password") String password) {

		UserLoginModel userLoginModel = new UserLoginModel();
		userLoginModel.setEmailid(emailid);
		userLoginModel.setPassword(password);

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();

		System.out.println("Current User " + currentUser);

		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");

		String str = (String) session.getAttribute("someKey");
		if (str.equals("aValue")) {

			System.out.println("Retrieved Correct value " + str);
		}

		boolean re = iUserLogin.checkUser(userLoginModel);
		if (re) {
			if (!currentUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(emailid, password);
				token.setRememberMe(true);
				try {
					currentUser.login(token);
				} catch (Exception ae) {
					System.out.println("Exception  " + ae);
				}
			}
			System.out.println("User [" + currentUser.getPrincipal() + "] logged in successfully.");
			return true;
		} else {
			System.out.println("User [" + currentUser.getPrincipal() + "] NOT logged in successfully.");
			return false;
		}

	}
}
