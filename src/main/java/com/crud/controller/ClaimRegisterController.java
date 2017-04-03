package com.crud.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.ClaimRegisterModel;
import com.crud.config.ApplicationConfig;
import com.crud.service.ICliamRegister;

@RestController
public class ClaimRegisterController {
	
	@Autowired
	ICliamRegister iCliamRegister;

	@RequestMapping(value = "/claimRegister", method = RequestMethod.POST)
	public @ResponseBody String myMethod(@RequestParam("recoverId") String recoverId, @RequestParam("claimNumber") String claimNumber,
			@RequestParam("policyNumber") String policyNumber, @RequestParam("locationCode") int locationCode,
			@RequestParam("totalAmountPaid") long totalAmountPaid, @RequestParam("insuredAddress") String insuredAddress,
			@RequestParam("dateOfAllotment") String dateOfAllotment, @RequestParam("rateOfInterest") int rateOfInterest,
			@RequestParam("insuredName") String insuredName, @RequestParam("interestAmount") double interestAmount)
			                          throws IOException {
		
		System.out.println("Welcome to Cliam Regitserd!");
		ClaimRegisterModel claimRegisterModel = new ClaimRegisterModel();
		claimRegisterModel.setRecoverId(recoverId);
		claimRegisterModel.setClaimNumber(claimNumber);
		claimRegisterModel.setPolicyNumber(policyNumber);
		claimRegisterModel.setLocationCode(locationCode);
		claimRegisterModel.setTotalAmountPaid(totalAmountPaid);
		claimRegisterModel.setInsuredAddress(insuredAddress);
		claimRegisterModel.setRateOfInterest(rateOfInterest);
		claimRegisterModel.setInsuredName(insuredName);
		claimRegisterModel.setInterestAmount(interestAmount);
		
		/*AbstractApplicationContext context = new
				  AnnotationConfigApplicationContext(ApplicationConfig.class);
		ICliamRegister iCliamRegister = (ICliamRegister) context.getBean("iCliamRegister");*/
		iCliamRegister.registerClaim(claimRegisterModel);
				
		return "Data Inserted Successfully!";
	}
}
