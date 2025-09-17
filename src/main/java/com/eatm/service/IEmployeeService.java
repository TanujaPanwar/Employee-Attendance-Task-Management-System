package com.eatm.service;

import com.eatm.entity.Address;
import com.eatm.entity.Employee;

public interface IEmployeeService {
	public void registerEmployee(Employee employee, Address address);
	public String loginEmployee(String email,String password);
    public String checkEmployeeEmail( String email);
    public String updateEmployeePassword(String password);
    public String createTask(String taskName);
    public String assignTask(int taskId,int employeeId);
    public void registerDeveloper(Employee employee, Address address);
    public String viewDeveloperWithTask() ;
    public String deleteDeveloper(int employeeId);
    public String logoutEmployee( int attendenceId);
    public String updateTaskStatus(int taskId);
    public String getDeveloper(int employeeId);
    public String saveData(int employeeId,String name,String emailId,String password);
    public String logoutDeveloper(int attendenceId);
   
    
}
