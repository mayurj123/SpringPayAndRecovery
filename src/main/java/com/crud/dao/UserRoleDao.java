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

	public UserRoleModel checkUserRole(UserRoleModel userRoleModel) {
		try{
		Query query = new Query(Criteria.where("emailid").is(userRoleModel.getUserLoggedIn()));
		/* query.fields().include("role"); */
		return mongoTemplate.findOne(query, UserRoleModel.class, "register_user");
		}catch(Exception e){
			System.out.println("Exception Occured in user role dao " +e);
			return null;
		}
		
	}
}
