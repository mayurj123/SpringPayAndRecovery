package com.crud.service;

import com.crud.bean.ClaimRegisterModel;

public interface ICliamRegister {
	public void registerClaim(ClaimRegisterModel crModel);
	public ClaimRegisterModel findClaim(ClaimRegisterModel crModel);
}
