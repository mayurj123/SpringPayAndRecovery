package com.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.crud.bean.RegisterUserModel;
import com.crud.service.IRegisterUser;

@Repository
public class RegisterUserDAO implements IRegisterUser {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public boolean registeruser(RegisterUserModel reguser) {
		
		try{
			Query query = new Query(Criteria.where("emailid").is(reguser.getEmailid()));
			List<RegisterUserModel> li = mongoTemplate.find(query, RegisterUserModel.class,"register_user");
			
			if(li.size()>0){
				return false;
			}else{
				mongoTemplate.insert(reguser,"register_user");
				return true;
			}
		
		}catch(Exception e){
			System.out.println("Sorry User Name Already Exist");
			return false;
		}
	}
}
