package com.traceAndControlSystem.service;

import java.util.List;

import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;

public interface TribeService {
	
	 List<Person> listPersonsByTribeNumber(String tribeNumber);
	 
	 void savePerson(List<Person> person);
	 
	 void saveTribe(Tribe tribe);
	 
	 List<Tribe> listTribe();

}
