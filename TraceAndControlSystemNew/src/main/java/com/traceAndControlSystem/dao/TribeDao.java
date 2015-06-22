package com.traceAndControlSystem.dao;

import java.util.List;

import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;

public interface TribeDao {
	List<Person> listPersonsByTribeNumber(Integer tribeNumber);
	 
	 void savePerson(List<Person> person);
	 
	 void saveTribe(Tribe tribe);
	 
	 List<Tribe> listTribe();

}
