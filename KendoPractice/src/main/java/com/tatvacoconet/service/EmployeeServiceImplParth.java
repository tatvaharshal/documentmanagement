package com.tatvacoconet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatvacoconet.dao.IEmployeeDAOParth;
import com.tatvacoconet.entity.EmployeeMasterParth;

/**
 * 
 * @author TatvaSoft
 *
 */
@Service
@Transactional
public class EmployeeServiceImplParth implements IEmployeeServiceParth {

	@Autowired
	private IEmployeeDAOParth employeeDAO;
	
	@Override
	public EmployeeMasterParth find(Integer employeeId) {
		return employeeDAO.findEntity(employeeId);
	}
	
	@Override
	public List<EmployeeMasterParth> getEmployeeMasterList() {
		return employeeDAO.getEmployeeMasterList();
	}
	
	@Override
	public void create(EmployeeMasterParth employeeMasterParth) {
		employeeDAO.create(employeeMasterParth);
	}
	
	@Override
	public void update(EmployeeMasterParth employeeMasterParth) {
		employeeDAO.update(employeeMasterParth);
	}

	@Override
	public void delete(Integer employeeId) {
		employeeDAO.delete(employeeId);
	}
}
