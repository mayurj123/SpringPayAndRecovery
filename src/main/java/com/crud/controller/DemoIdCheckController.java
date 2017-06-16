package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.DemoModel;
import com.crud.service.IDemoCheck;

@RestController
public class DemoIdCheckController {

	@Autowired
	IDemoCheck iDemoCheck;
	
	@RequestMapping(value="/demoCheck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String addDemoIdDetails(@RequestParam String userName){
		DemoModel demoModel = new DemoModel();
		demoModel.setUsername(userName);
		//demoModel.setId(id);
		
		iDemoCheck.addDemoIdCheck(demoModel);
		return "Record inserted!";
	}
	
	@RequestMapping(value="/findDemoCheck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<DemoModel> findDemoIdDetails(@RequestParam String userName){
		DemoModel demoModel = new DemoModel();
		demoModel.setUsername(userName);
		//demoModel.setId(id);
		List<DemoModel> list= iDemoCheck.findDemoIdCheck(demoModel);
				
		return list;
	}
	
}
