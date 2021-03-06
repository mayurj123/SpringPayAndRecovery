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


public class ApacheShiroTest {
	private static final transient Logger log = LoggerFactory.getLogger(ApacheShiroTest.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("My First Apache Shiro Application");
		log.info("My First Apache Shiro Application");
		
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		
		System.out.println("Current User "+currentUser);
		
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		
		String str = (String) session.getAttribute("someKey");
		if(str.equals("aValue")){
			log.info("Retrieved Correct value "+ str);
			System.out.println("Retrieved Correct value "+ str);
		}
		
	     // let's login the current user so we can check against roles and permissions:
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
            	System.out.println("There is no user with username of " + token.getPrincipal());
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
            	System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
            	System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        System.out.println("User [" + currentUser.getPrincipal() + "] logged in successfully.");
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        if (currentUser.hasRole("schwartz")) {
        	System.out.println("May the Schwartz be with you!");
            log.info("May the Schwartz be with you!");
        } else {
        	System.out.println("Hello, mere mortal.");
            log.info("Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("lightsaber:wield")) {
        	System.out.println("You may use a lightsaber ring.  Use it wisely.");
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
        	System.out.println("Sorry, lightsaber rings are for schwartz masters only.");
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
        	System.out.println("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
        	System.out.println("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //all done - log out!
        currentUser.logout();

        System.exit(0);
		
	}

}
