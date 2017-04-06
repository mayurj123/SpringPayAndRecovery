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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crud.bean.GenericResponse;
import com.crud.bean.LoggingResponse;
import com.crud.bean.UserLoginModel;
import com.crud.service.IUserLogin;

@RestController
public class UserLoginAuthenticationController {
	  private static Logger logger = LoggerFactory.getLogger(UserLoginAuthenticationController.class);

	@Autowired
	IUserLogin iUserLogin;

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody LoggingResponse loginHandler(@RequestParam("emailid") String emailid, @RequestParam("password") String password) {

		UserLoginModel userLoginModel = new UserLoginModel();
		userLoginModel.setEmailid(emailid);
		userLoginModel.setPassword(password);

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		
		//boolean re = iUserLogin.checkUser(userLoginModel);
		/*if (re) {*/
			if (!currentUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(emailid, password);
				token.setRememberMe(true);
				System.out.println("User [" + currentUser.getPrincipal() + "] logged in successfully.");
				try {
					currentUser.login(token);
				} catch (AuthenticationException ae) {
					System.out.println("Exception  " + ae);
					logger.debug("Failed to authenticate user {}", emailid, ae);
			        logger.error("Failed to authenticate user {}", emailid);
					//throw new UnauthenticatedException("Failed to authenticate user '" + emailid + "' " +  ae.getMessage());
				}
			/*}*/
			
		} 
		String sessionId = (String) currentUser.getSession().getId();
		LoggingResponse loggingResponse = new LoggingResponse(sessionId);
	    return loggingResponse;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody GenericResponse logoutHandler() {

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("hi...");
		
		String sessionId = (String) currentUser.getSession().getId();
		LoggingResponse loggingResponse = new LoggingResponse(sessionId);
	    System.out.println("loggingResponse "+loggingResponse);
		
	    if (currentUser.isAuthenticated()) {
			System.out.println("vyby");
			try {
				UserLoginModel ua = (UserLoginModel) SecurityUtils.getSubject().getPrincipal();
				SecurityUtils.getSubject().logout();
				System.out.println("successfully logged out.");
				
				return new GenericResponse("User: '" + ua.getEmailid() + "' successfully logged out.");
			} catch (AuthenticationException ae) {
				System.out.println("Exception " + ae);
				throw ae;
			}
		} 
			return new GenericResponse("No active session, ignoring logout.");
	}

}
