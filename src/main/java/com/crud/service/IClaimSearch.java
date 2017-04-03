package com.crud.service;

import java.util.List;

import com.crud.bean.ClaimSearchModel;

public interface IClaimSearch {
	public List<ClaimSearchModel> searchClaim(ClaimSearchModel claimSearchModel);
	public List<ClaimSearchModel> searchClaimDetails(ClaimSearchModel claimSearchModel);
}
