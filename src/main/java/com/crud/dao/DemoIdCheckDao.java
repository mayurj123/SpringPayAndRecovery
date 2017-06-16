package com.crud.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.crud.bean.DemoModel;
import com.crud.service.IDemoCheck;

@Repository
public class DemoIdCheckDao implements IDemoCheck{

	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public void addDemoIdCheck(DemoModel demoModel) {

		mongoTemplate.insert(demoModel);
		System.out.println("data inserted");
	}
	@Override
	public List<DemoModel> findDemoIdCheck(DemoModel demoModel) {
		Query query = new Query(Criteria.where("username").is(demoModel.getUsername()));
		List<DemoModel> list = mongoTemplate.find(query, DemoModel.class);
		return list;
	}

}
