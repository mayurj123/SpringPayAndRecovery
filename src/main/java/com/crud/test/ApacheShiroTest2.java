package com.crud.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


public class ApacheShiroTest2 {
	private static final transient Logger log = LoggerFactory.getLogger(ApacheShiroTest.class);

	public static void main(String[] args) {
	
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		
		String str = (String) session.getAttribute("someKey");
		if(str.equals("aValue")){
			System.out.println("Retrieved Correct value "+ str);
		}
		    if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } 
            catch (Exception ae) {
                System.out.println("Exception "+ae);
            }
        }
        System.out.println("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //Role
        if (currentUser.hasRole("schwartz")) {
        	System.out.println("May the Schwartz be with you!");
        } else {
        	System.out.println("Hello, mere mortal.");
        }
        
        //permission
        if (currentUser.isPermitted("lightsaber:wield")) {
        	System.out.println("You may use a lightsaber ring.  Use it wisely.");
        } else {
        	System.out.println("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
        	System.out.println("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
        	System.out.println("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        	}

        //all done - log out!
        currentUser.logout();
        System.exit(0);
	}
}
