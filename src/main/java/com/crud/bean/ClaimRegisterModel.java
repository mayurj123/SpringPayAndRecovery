package com.crud.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "claim_register")
public class ClaimRegisterModel {
	@Id
	private String id;
	
	@Indexed(unique=true)
	String recoverId;
	
	@Indexed(unique=true)
	String claimNumber;
	@Indexed(unique=true)
	String policyNumber;
	
	int locationCode;
	long totalAmountPaid;
	String insuredAddress;
	String dateOfAllotment;
	int rateOfInterest;
	String insuredName;
	double interestAmount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecoverId() {
		return recoverId;
	}
	public void setRecoverId(String recoverId) {
		this.recoverId = recoverId;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public int getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}
	public long getTotalAmountPaid() {
		return totalAmountPaid;
	}
	public void setTotalAmountPaid(long totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}
	public String getInsuredAddress() {
		return insuredAddress;
	}
	public void setInsuredAddress(String insuredAddress) {
		this.insuredAddress = insuredAddress;
	}
	public String getDateOfAllotment() {
		return dateOfAllotment;
	}
	public void setDateOfAllotment(String dateOfAllotment) {
		this.dateOfAllotment = dateOfAllotment;
	}
	public int getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(int rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public double getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}
	
	
}
