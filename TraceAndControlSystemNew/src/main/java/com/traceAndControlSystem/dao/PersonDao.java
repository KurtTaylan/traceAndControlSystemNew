package com.traceAndControlSystem.dao;

import java.util.List;

import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;

public interface PersonDao {
	List<Person> listPersonsByTribeNumber(String tribeNumber);
    
    Person findPersonById(String Id);
    
    List<Person> listPersons();
    
    void savePerson(Person person);
    
    void saveTribe(Tribe tribe);

}
