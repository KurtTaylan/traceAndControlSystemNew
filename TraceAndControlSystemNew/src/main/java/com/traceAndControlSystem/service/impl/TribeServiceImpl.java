package com.traceAndControlSystem.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.traceAndControlSystem.dao.TribeDao;
import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;
import com.traceAndControlSystem.service.TribeService;

public class TribeServiceImpl implements TribeService {
	
	private TribeDao tribeDao;
	private Integer numberOfPeople;

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public TribeDao getTribeDao() {
		return tribeDao;
	}

	public void setTribeDao(TribeDao tribeDao) {
		this.tribeDao = tribeDao;
	}
	@Transactional
	public List<Person> listPersonsByTribeNumber(Integer tribeNumber) {
		
		return getTribeDao().listPersonsByTribeNumber(tribeNumber);
	}
	@Transactional
	public void savePerson(List<Person> person) {
		getTribeDao().savePerson(person);
		
	}
	@Transactional
	public void saveTribe(Tribe tribe) {
		getTribeDao().saveTribe(tribe);
		
	}
	@Transactional
	public List<Tribe> listTribe() {
		
		return getTribeDao().listTribe();
	}
	
	public int numberOfTribe(){
		List<Tribe> list = listTribe();
		setNumberOfPeople(list.size());
		return list.size();
	}

}
