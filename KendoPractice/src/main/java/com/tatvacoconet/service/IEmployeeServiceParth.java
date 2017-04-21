package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.EmployeeMasterParth;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IEmployeeServiceParth {
	
	public List<EmployeeMasterParth> getEmployeeMasterList();
	
	public EmployeeMasterParth find(Integer employeeId);
	
	public void create(EmployeeMasterParth employeeMasterParth);
	
	public void delete(Integer employeeId);
	
	public void update(EmployeeMasterParth employeeMasterParth);

}
