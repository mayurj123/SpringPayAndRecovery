package com.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.crud.bean.ClaimRegisterModel;
import com.crud.bean.RegisterUserModel;
import com.crud.service.ICliamRegister;

@Service("iCliamRegister")
@Repository()
public class ClaimRegisterDao implements ICliamRegister {
	@Autowired
	MongoTemplate mongoTemplate;

	public boolean registerClaim(ClaimRegisterModel crModel) {
		try {
			Criteria criteria = new Criteria();
			Query query = new Query(criteria.andOperator(Criteria.where("recoverId").is(crModel.getRecoverId()),
					Criteria.where("claimNumber").is(crModel.getClaimNumber()),
					Criteria.where("policyNumber").is(crModel.getPolicyNumber())));
			List<ClaimRegisterModel> li = mongoTemplate.find(query, ClaimRegisterModel.class, "claim_register");

			if (li.size() > 0) {
				return false;
			} else {
				mongoTemplate.insert(crModel, "claim_register");
				return true;
			}

		} catch (Exception e) {
			System.out.println("Exceptin Occured "+e);
			return false;
		}
	}
}
