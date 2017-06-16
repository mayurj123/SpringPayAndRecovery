package com.crud.service;

import java.util.List;

import com.crud.bean.DemoModel;

public interface IDemoCheck {

	public void addDemoIdCheck(DemoModel demoModel);
	public List<DemoModel> findDemoIdCheck(DemoModel demoModel);
}
