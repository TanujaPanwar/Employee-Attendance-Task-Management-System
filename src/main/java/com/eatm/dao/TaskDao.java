package com.eatm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eatm.entity.Task;

@Component
public class TaskDao {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EntityTransaction entityTransaction;
	
	public Task saveTask(Task task)
	{
		entityTransaction.begin();
		entityManager.persist( task);
		entityTransaction.commit();
		return task;
	}
	
	public Task updateTask(Task  task)
	{
		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
		return task;
	}
	
	public Task deleteTask(Task task)
	{
		entityTransaction.begin();
		entityManager.remove(task);
		entityTransaction.commit();
		return  task;
	}
	public Task findTaskById(int taskid) {
		return entityManager.find(Task.class,taskid);
	}
}
