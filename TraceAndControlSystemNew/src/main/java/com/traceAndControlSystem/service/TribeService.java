package com.traceAndControlSystem.service;

import java.util.List;

import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;

public interface TribeService {
	
	 List<Person> listPersonsByTribeNumber(Integer tribeNumber);
	 
	 void savePerson(List<Person> person);
	 
	 void saveTribe(Tribe tribe);
	 
	 List<Tribe> listTribe();
	 
	 int getNumberOfTribeMember(String tribeName);
	 
	void saveAddress(String address);

	Tribe getTribeById(int id);
}
