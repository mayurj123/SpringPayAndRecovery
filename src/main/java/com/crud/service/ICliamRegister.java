package com.crud.service;

import java.util.List;

import com.crud.bean.ClaimRegisterModel;

public interface ICliamRegister {
	public void registerClaim(ClaimRegisterModel crModel);
	public ClaimRegisterModel findClaim(ClaimRegisterModel crModel);
}
