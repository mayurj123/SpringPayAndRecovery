package com.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.crud.bean.UserLoginModel;
import com.crud.service.IUserLogin;

@Repository
public class UserLoginDao implements IUserLogin {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public boolean checkUser(UserLoginModel userLoginModel) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria();
		Query query = new Query(criteria.andOperator(Criteria.where("emailid").is(userLoginModel.getEmailid()), Criteria.where("password").is(userLoginModel.getPassword())));
		/*Query query = new Query(Criteria.where("emailid").is(userLoginModel.getEmailid()));*/
		List<UserLoginModel> li = mongoTemplate.find(query, UserLoginModel.class,"register_user");
		
		System.out.println("li "+li);
		if(li.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
}
