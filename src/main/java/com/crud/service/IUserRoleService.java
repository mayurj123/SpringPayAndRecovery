package com.crud.service;

import com.crud.bean.RegisterUserModel;
import com.crud.bean.UserRoleModel;

public interface IUserRoleService {
	public RegisterUserModel checkUserRole(UserRoleModel userRoleModel);
}
