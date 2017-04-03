package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.ClaimSearchModel;
import com.crud.service.IClaimSearch;

@RestController
public class ClaimSearchController {
	
	@Autowired
	IClaimSearch iClaimSearch;
	
	@RequestMapping(value="/claimSearch", method= RequestMethod.GET)
	public List<ClaimSearchModel> searchClaim(@RequestParam("recoverId") String recoverId, @RequestParam("claimNumber") String claimNumber){
		
		ClaimSearchModel claimSearchModel = new ClaimSearchModel();
		claimSearchModel.setRecoverId(recoverId);
		claimSearchModel.setClaimNumber(claimNumber);
		/*claimSearchModel.setInsuredName(insuredName);
		claimSearchModel.setLocationCode(locationCode);*/
		
		List<ClaimSearchModel> claimSearchResultList = iClaimSearch.searchClaim(claimSearchModel);	
		System.out.println("claimSearchResultList "+claimSearchResultList);
		return claimSearchResultList;
	}
	
	@RequestMapping(value="/claimDetails", method= RequestMethod.GET)
	public List<ClaimSearchModel> claimDetails(@RequestParam("recoverId") String recoverId){
		
		ClaimSearchModel claimSearchModel = new ClaimSearchModel();
		claimSearchModel.setRecoverId(recoverId);
			
		List<ClaimSearchModel> claimSearchResultList = iClaimSearch.searchClaimDetails(claimSearchModel);	
		System.out.println("claimSearchResultList "+claimSearchResultList);
		return claimSearchResultList;
	}
}
