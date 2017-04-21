package com.tatvacoconet.dao;

import java.util.List;

import com.tatvacoconet.entity.EmployeeMasterParth;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IEmployeeDAOParth {
	
	public EmployeeMasterParth findEntity(Integer employeeId);
	
	public List<EmployeeMasterParth> getEmployeeMasterList();
	
	public void create(EmployeeMasterParth employeeMasterParth);
	
	public void update(EmployeeMasterParth employeeMasterParth);
	
	public void delete(Integer employeeId);
}
