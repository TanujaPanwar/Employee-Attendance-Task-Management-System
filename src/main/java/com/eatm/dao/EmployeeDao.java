package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eatm.entity.Attendence;
import com.eatm.entity.Employee;

@Component
public class EmployeeDao {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EntityTransaction entityTransaction;
	
	public Employee saveEmployee(Employee employee)
	{
		entityTransaction.begin();
		entityManager.persist(employee);
		entityTransaction.commit();
		return  employee;
	}
	public Employee updateEmployee(Employee employee) {
		entityTransaction.begin();
		entityManager.merge(employee);
		entityTransaction.commit();
		return employee;
	}
	
	public Employee deleteEmployee(Employee employee) {
		entityTransaction.begin();
		entityManager.remove(employee);
		entityTransaction.commit();
		return employee;
	}
	

       public Employee findEmployeeById(int empid) {
	  return entityManager.find(Employee.class,empid);
      }
       
       public List<Employee> findAllEmployee(){
    	   Query query=entityManager.createQuery
   				("select e from Employee e ");
    	   List<Employee> list = query.getResultList();
    	   return list;
       }
	

	public Employee findEmployeeByEmailAndPassword(String email, String password) 
	{
		Query query=entityManager.createQuery
				("select e from Employee e where e.emailId=?1 and e.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<Employee> list=query.getResultList();
		if(list.size()>0) 
			return list.get(0);
		
		else
			return null;
	}
	
	public Employee findEmployeeByEmail(String email) {
		Query query=entityManager.createQuery
				("select e from Employee e where e.emailId = ?1");
		query.setParameter(1, email);
		List<Employee> list=query.getResultList();
		if(list.size()>0) 
			return list.get(0);
		
		else
			return null;
	}
	
	public List<Attendence> fetchAttendenceByUserId(int id){
		Employee employee = findEmployeeById(id);
		return employee.getAttendences();
	}

	public List<Employee> findAllDevelopersWithTasks() {
		Query query=entityManager.createQuery
				("select e from Employee e where e.role = ?1");
				query.setParameter(1, "developer");
		List <Employee> list = query.getResultList();
		return  list;
		
	   
	}




}
