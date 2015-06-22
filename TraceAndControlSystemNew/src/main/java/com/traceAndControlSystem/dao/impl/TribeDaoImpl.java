package com.traceAndControlSystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.traceAndControlSystem.dao.TribeDao;
import com.traceAndControlSystem.model.Person;
import com.traceAndControlSystem.model.Tribe;

public class TribeDaoImpl implements TribeDao{
	
	private SessionFactory sessionFactory;

	
	public List<Person> listPersonsByTribeNumber(Integer tribeNumber) {
		
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Tribe.class);
        criteria.add(Restrictions.eq("id",tribeNumber));
        
        Tribe tribe = (Tribe)criteria.uniqueResult();
        List<Person> pl = tribe.getPersonList();
        
        return pl;
	}

	public void savePerson(List<Person> person) {
		getSessionFactory().getCurrentSession().persist(person);
	}

	public void saveTribe(Tribe tribe) {
		getSessionFactory().getCurrentSession().persist(tribe);
	}

	@SuppressWarnings("unchecked")
	public List<Tribe> listTribe() {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Tribe.class);
        return (List<Tribe>) criteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
