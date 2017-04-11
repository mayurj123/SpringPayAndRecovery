package com.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.crud.bean.ClaimSearchModel;
import com.crud.service.IClaimSearch;

/*@Service("claimSearch")*/
@Service
@Repository
public class ClaimSearchDao implements IClaimSearch {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<ClaimSearchModel> searchClaim(ClaimSearchModel claimSearchModel) {
		Criteria criteria = new Criteria();
		Query query = new Query(criteria.orOperator(Criteria.where("recoverId").is(claimSearchModel.getRecoverId()), Criteria.where("claimNumber").is(claimSearchModel.getClaimNumber())));
		return mongoTemplate.find(query, ClaimSearchModel.class,"claim_register");
	}
	public List<ClaimSearchModel> searchClaimDetails(ClaimSearchModel claimSearchModel) {
		Query query = new Query(Criteria.where("recoverId").is(claimSearchModel.getRecoverId()));
		return mongoTemplate.find(query, ClaimSearchModel.class,"claim_register");
	}
}
	
	
