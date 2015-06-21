package com.traceAndControlSystem.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.traceAndControlSystem.dao.PersonDao;
import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;
import com.traceAndControlSystem.service.PersonService;

public class PersonServiceImpl implements PersonService {
	
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	@Transactional
	public List<Person> listPersonsByTribeNumber(String tribeNumber) {
		
		return getPersonDao().listPersonsByTribeNumber(tribeNumber);
	}
	@Transactional
	public Person findPersonById(String Id) {
		
		return getPersonDao().findPersonById(Id);
	}
	@Transactional
	public List<Person> listPersons() {
		
		return getPersonDao().listPersons();
	}
	@Transactional
	public void savePerson(Person person) {
		getPersonDao().savePerson(person);
		
	}
	@Transactional
	public void saveTribe(Tribe tribe) {
		getPersonDao().saveTribe(tribe);
		
	}

}
