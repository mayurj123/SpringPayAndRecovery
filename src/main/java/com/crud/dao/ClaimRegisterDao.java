package com.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.crud.bean.ClaimRegisterModel;
import com.crud.service.ICliamRegister;

@Service("iCliamRegister")
@Repository()
public class ClaimRegisterDao implements ICliamRegister{
	@Autowired
	MongoTemplate mongoTemplate;
	
	final String COLLECTION = "claim_register";
	
	@Override
	public void registerClaim(ClaimRegisterModel crModel) {
		// TODO Auto-generated method stub
	  mongoTemplate.insert(crModel, "claim_register");	
	}
	@Override
	public ClaimRegisterModel findClaim(ClaimRegisterModel crModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
