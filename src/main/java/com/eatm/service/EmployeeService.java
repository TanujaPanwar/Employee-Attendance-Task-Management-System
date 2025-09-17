package com.eatm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.eatm.dao.AddressDao;
import com.eatm.dao.AttendenceDao;
import com.eatm.dao.EmployeeDao;
import com.eatm.dao.TaskDao;
import com.eatm.entity.Address;
import com.eatm.entity.Attendence;
import com.eatm.entity.Employee;
import com.eatm.entity.Status;
import com.eatm.entity.Task;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class EmployeeService implements IEmployeeService{
    @Autowired
	EmployeeDao employeeDao;
    @Autowired
	AddressDao addressDao;
    @Autowired
    Attendence attendence;
    @Autowired
    TaskDao taskDao;
    @Autowired
    AttendenceDao attendenceDao;
    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpSession session;
    
    public void registerEmployee(Employee employee, Address address) {
    	addressDao.saveAddress(address);
    	employee.setAddress(address);
    	employee.setRole("manager");
    	employeeDao.saveEmployee(employee);
    	
    }
    

    
        public String checkEmployeeEmail(String email) {
        	Employee employeeByEmail = employeeDao.findEmployeeByEmail(email);
        	session.setAttribute("employeeByEmail",employeeByEmail);
        	if(employeeByEmail!=null) {
        		return "takeNewPassword";
        	}
        	request.setAttribute("errorMessage","Invalid Email id");
        	return "checkEmail";
        }
        
        public String updateEmployeePassword(String password) {
        	Employee employeeFromDb =(Employee)session.getAttribute("employeeByEmail");
        	employeeFromDb.setPassword(password);
        	employeeDao.updateEmployee(employeeFromDb);
        	request.setAttribute("updationMessage", "Password Updated Successfully");
        	return "login";
        }
        public String loginEmployee(String email, String password) {
        	//fetching employee based on email and password
            Employee dbEmployee = employeeDao.findEmployeeByEmailAndPassword(email, password);

            if (dbEmployee != null) {
                // saving attendance, update employee, etc.
                attendenceDao.saveAttendence(attendence);
              //for the employee getting the old list of attendence
                List<Attendence> listAttendences = dbEmployee.getAttendences();
              //if old list is not there i am creating new list
            	if (listAttendences == null) {
                    listAttendences = new ArrayList<>();
                }
            	//adding current attendence to the list
                listAttendences.add(attendence);
              //setting list of attendence to same employee
                dbEmployee.setAttendences(listAttendences);

          		//updating employee
                employeeDao.updateEmployee(dbEmployee);

                request.setAttribute("message", "Login success");

                if (dbEmployee.getRole().equals("manager")) {
                	
                	//manager operation
                	/*
                	 * 1.create task
                	 * 2.assign task
                	 * 3.add developer
                	 * 4.view all developers with task
                	 * 5.delete task
                	 * 6.logout
                	 */
                    return "manager";
                } else if (dbEmployee.getRole().equals("developer")) {
                	  //developer operation
                	/*
                	 * 1.create task status
                	 * 2.update details
                	 * 3.logout
                	 */
                    return "developer";
                } else {
                    request.setAttribute("errorMessage", "Invalid role");
                    return "login";
                }
            } else {
                //  credentials wrong -- go back to login page
                request.setAttribute("errorMessage", "Invalid email or password");
                return "login";
            }
        
        }
        
   	 public String createTask(String taskName) {
            Task task = new Task();
            task.setTaskName(taskName);
         

            taskDao.saveTask(task);
            return "taskCreatedSuccess";
            		
        }
   	 // assign task to employee by ID
     public String assignTask(int taskId, int employeeId) {
    	 
         Task task = taskDao.findTaskById(taskId);
         Employee employee = employeeDao.findEmployeeById(employeeId);
        // create task list
         List<Task> tasks = employee.getTasks();
         if(tasks == null) {
             tasks = new ArrayList<>();
         }

         // Add current task to list
         tasks.add(task);
         employee.setTasks(tasks);
        task.setStatus(Status.ASSIGNED);     
        employeeDao.updateEmployee(employee);
        taskDao.updateTask(task);

         return "taskAssignSuccess";
     }

     public void registerDeveloper(Employee employee, Address address) {
     	addressDao.saveAddress(address);
     	employee.setAddress(address);
     	employee.setRole("developer");
     	employeeDao.saveEmployee(employee);
     	
     }
     
     public String viewDeveloperWithTask() {
    	  List<Employee> developers = employeeDao.findAllDevelopersWithTasks();

	      
	        request.setAttribute("developers", developers);
	        return "DisplayAllDeveloperWithTask";
     }
     public String deleteDeveloper(int employeeId) {
    	Employee employee = employeeDao.findEmployeeById(employeeId);
       employeeDao.deleteEmployee(employee);
         return "DeleteDeveloperSuccess";
     }
     public String logoutEmployee(int attendenceId) {
    	 Attendence attendence =attendenceDao.findById(attendenceId);
    	 if(attendence!=null) {
    	 attendence.setLogoutTime(LocalDateTime.now()); // set current logout time
         attendenceDao.saveAttendence(attendence);
         attendenceDao.updateAttendence(attendence);
    	 }
    	 else {
    		 request.setAttribute("errorMessage", "Invalid attendence Id");
    	        return "logout";
    	 }
         return "logoutSuccess";
     }

    public String updateTaskStatus(int taskId) {
    	Task task = taskDao.findTaskById(taskId);
    	if(task!=null) {
    		 task.setStatus(Status.COMPLETED);     
    	        taskDao.updateTask(task);
    	        
    	}
    	
    	 else {
    		 request.setAttribute("errorMessage", "Invalid taskId");
             return "createTaskStatus";
    	 }
    	 return "updateTaskStatus";
    }
    public String getDeveloper(int employeeId) {
    	Employee employee = employeeDao.findEmployeeById(employeeId);
    	return "updationForm";
    }
    public String saveData(int employeeId,String name,String emailId,String password) {
    	Employee employee=employeeDao.findEmployeeById(employeeId);
    	if(employee!=null) {
    		employee.setName(name);
        	employee.setEmailId(emailId);
        	employee.setPassword(password);
        	employeeDao.updateEmployee(employee);
    	}else {
    		request.setAttribute("errorMessage", "Invalid employee Id");
            return "updateDetails";
    	}
    	
    	return "saveDeveloperData";
    }
   


    public String logoutDeveloper(int attendenceId) {
   	 Attendence attendence =attendenceDao.findById(attendenceId);
   	 if(attendence!=null) {
   	  // Set logout time
     attendence.setLogoutTime(LocalDateTime.now());
     attendenceDao.updateAttendence(attendence);
   	 }
   	 else
   	 { 
   		request.setAttribute("errorMessage", "Invalid attendence Id");
        return "logoutDeveloper"; 
   	 }
     return "logoutDeveloperSuccess"; 
  
    }

	




	

}
  	
  		
  	

  	

   
  

    
    
    
    
 