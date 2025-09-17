package com.eatm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eatm.dao.EmployeeDao;
import com.eatm.entity.Address;
import com.eatm.entity.Employee;
import com.eatm.service.IEmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller

public class EmployeeController {
	 @Autowired
	    IEmployeeService employeeService;
	 @Autowired
	   IEmployeeService taskService; 
     @Autowired
      IEmployeeService attendenceService;	   
	    @GetMapping("/register")
	    public String registerEmployee(@ModelAttribute Employee employee, @ModelAttribute Address address) {
	    	//read data from form directly in object format
	        employeeService.registerEmployee(employee, address);
	        return "registerSuccess";
	    }

	
	    @GetMapping("/login")
	    public String loginEmployee(@RequestParam("emailId") String email,@RequestParam("password") String password) {
	    	return employeeService.loginEmployee(email,password);
	       
	    }
	    
	    @GetMapping("/checkEmail")
	    public String checkEmployeeEmail(@RequestParam("email") String email) {
	    	return employeeService.checkEmployeeEmail(email);
	       
	    }
	    @GetMapping("/updatePassword")
	    public String updateEmployeePassword(@RequestParam("password") String password) {
	    	return employeeService.updateEmployeePassword(password);
	       
	    }
	    @GetMapping("/createTask")
	    public String createTask(@RequestParam("taskName") String taskName)
               {
	    	return taskService.createTask(taskName);
               }
	    
	    @GetMapping("/assignTask")
	    public String assignTask(@RequestParam("taskId") int taskId,
	                             @RequestParam("employeeId") int employeeId) {

	    
	    	return taskService.assignTask(taskId,employeeId);
	    }
	    @GetMapping("/registerDeveloper")
	    public String registerDeveloper(@ModelAttribute Employee employee, @ModelAttribute Address address) {
	    	//read data from form directly in object format
	        employeeService.registerDeveloper(employee, address);
	        return "registerDeveloperSuccess";
	    }
	   
	    @GetMapping("/viewDeveloperWithTask")
	    public String viewDeveloperWithTask() {
	     return employeeService.viewDeveloperWithTask();
	    }
	    @GetMapping("/deleteDeveloper")
	    public String deleteDeveloper(@RequestParam("employeeId") int employeeId) {
	        return employeeService.deleteDeveloper(employeeId);
	    }

	      @GetMapping("/logout")
	      public String logoutEmployee(@RequestParam ("attendenceId") int attendenceId) {
	    	 return attendenceService.logoutEmployee(attendenceId);
	      }
	      
	      @GetMapping("/updateTask")
	      public String updateTaskStatus(@RequestParam ("taskId") int taskId) {
	    	  return taskService.updateTaskStatus(taskId);
	      }
	     @GetMapping("/getDeveloper")
	     public String getDeveloper(@RequestParam("employeeId") int employeeId) {
	    	 return employeeService.getDeveloper(employeeId);
	     }
	     @GetMapping("/saveData")
	     public String saveData(@RequestParam("employeeId")int employeeId,@RequestParam("name")String name,
	    		 @RequestParam("emailId")String emailId,@RequestParam("password")String password) {
	    	return employeeService.saveData(employeeId,name,emailId,password); 
	     }
	    
	      @GetMapping("/logoutDeveloper")
	      public String logoutDeveloper(@RequestParam ("attendenceId") int attendenceId) {
	    	 return attendenceService.logoutDeveloper(attendenceId);
	      }

       
}
