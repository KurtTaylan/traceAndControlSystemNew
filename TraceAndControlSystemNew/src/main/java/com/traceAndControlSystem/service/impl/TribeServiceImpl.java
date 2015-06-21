package com.traceAndControlSystem.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.traceAndControlSystem.dao.TribeDao;
import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;
import com.traceAndControlSystem.service.TribeService;

public class TribeServiceImpl implements TribeService {
	
	private TribeDao tribeDao;

	public TribeDao getTribeDao() {
		return tribeDao;
	}

	public void setTribeDao(TribeDao tribeDao) {
		this.tribeDao = tribeDao;
	}
	@Transactional
	public List<Person> listPersonsByTribeNumber(String tribeNumber) {
		
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

}
