package com.eatm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eatm.entity.Attendence;

@Component
public class AttendenceDao {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EntityTransaction entityTransaction;
	
	public Attendence saveAttendence(Attendence attendence)
	{
		entityTransaction.begin();
		entityManager.persist(attendence);
		entityTransaction.commit();
		return attendence;
	}
	
	public Attendence updateAttendence(Attendence attendence)
	{
		entityTransaction.begin();
		entityManager.merge(attendence);
		entityTransaction.commit();
		return attendence;
	}
	
	public Attendence deleteAttendence(Attendence attendence)
	{
		entityTransaction.begin();
		entityManager.remove(attendence);
		entityTransaction.commit();
		return attendence;
	}
	public Attendence findById(int id) {
		return entityManager.find(Attendence.class, id);
	}

}
