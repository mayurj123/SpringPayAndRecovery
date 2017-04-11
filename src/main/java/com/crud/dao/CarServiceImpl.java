package com.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.crud.bean.Car;
import com.crud.service.CarService;

@Service("carService")
@Repository()
public class CarServiceImpl implements CarService {

	@Autowired
	MongoTemplate mongoTemplate;

	final String COLLECTION = "cars";

	public void create(Car car) {
		mongoTemplate.insert(car);
	}

	public void update(Car car) {
		mongoTemplate.save(car);
	}

	public void delete(Car car) {
		mongoTemplate.remove(car);
	}

	public void deleteAll() {
		mongoTemplate.remove(new Query(), COLLECTION);
	}

	public Car find(Car car) {
		Query query = new Query(Criteria.where("_id").is(car.getId()));
		return mongoTemplate.findOne(query, Car.class, COLLECTION);
	}

	public List<Car> findAll() {
		return (List<Car>) mongoTemplate.findAll(Car.class);
	}

}
