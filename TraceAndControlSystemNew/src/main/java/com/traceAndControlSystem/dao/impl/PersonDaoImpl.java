package com.traceAndControlSystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.traceAndControlSystem.dao.PersonDao;
import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;

public class PersonDaoImpl implements PersonDao {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Person> listPersonsByTribeNumber(Tribe tribeNumber) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.eq("tribe",tribeNumber));
        return (List<Person>)criteria.list();
	}

	public Person findPersonById(String Id) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.eq("personId",Id));
        return (Person)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPersons() {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Person.class);
        return (List<Person>) criteria.list();
	}

	public void savePerson(Person person) {
		getSessionFactory().getCurrentSession().save(person);
		
	}

	public void saveTribe(Tribe tribe) {
		getSessionFactory().getCurrentSession().save(tribe);
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
