package com.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.crud.bean.RegisterUserModel;
import com.crud.bean.UserRoleModel;
import com.crud.service.IUserRoleService;

@Repository
public class UserRoleDao implements IUserRoleService {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public RegisterUserModel checkUserRole(UserRoleModel userRoleModel) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("emailid").is(userRoleModel.getUserLoggedIn()));
	/*	query.fields().include("role");*/
		return mongoTemplate.findOne(query, RegisterUserModel.class,"register_user");
	}
}
