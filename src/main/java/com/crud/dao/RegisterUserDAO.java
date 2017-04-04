package com.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.crud.bean.RegisterUserModel;
import com.crud.service.IRegisterUser;

@Repository
public class RegisterUserDAO implements IRegisterUser {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public String registeruser(RegisterUserModel reguser) {
		
		mongoTemplate.insert(reguser,"register_user");
		return "inserted successfully";
	}

	
}
