package com.crud.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.bean.Car;
import com.crud.bean.ClaimRegisterModel;
import com.crud.config.ApplicationConfig;
import com.crud.service.CarService;
import com.crud.service.ICliamRegister;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	
	@Autowired
	CarService carService;

	@RequestMapping(value = "/myController", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Car myMethod(@RequestParam("brand") String brand,
			                          @RequestParam("model") String model)
			                          throws IOException {

		/*
		 * AbstractApplicationContext context = new
		 * AnnotationConfigApplicationContext(ApplicationConfig.class);
		 * CarService carService = (CarService) context.getBean("carService");
		 */

		Car car = new Car(brand, model);

		carService.create(car);

		/* List<Car> li = carService.findAll(); */

		Car str = carService.find(car);

		System.out.println("car created inserted record!!!!!!");
		if (str != null) {
			System.out.println(str);
			return str;

		} else {
			return str;
		}
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}
