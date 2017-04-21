package com.tatvacoconet.dao;

import java.util.List;

import org.hibernate.sql.Delete;
import org.springframework.stereotype.Repository;

import com.tatvacoconet.entity.DashboardWidgetMaster;
import com.tatvacoconet.entity.EmployeeMasterParth;
import com.tatvacoconet.entity.WidgetFormatMaster;

/**
 * 
 * @author TatvaSoft
 *
 */
@Repository
public class EmolpyeeDAOImplParth extends TatvaSoftDAOImpl<EmployeeMasterParth, Integer> implements IEmployeeDAOParth{

	public EmolpyeeDAOImplParth() {
		super(EmployeeMasterParth.class);
	}

	@Override
	public EmployeeMasterParth findEntity(Integer employeeId) {
		return find(employeeId);
	}
	
	@Override
	public void delete(Integer employeeId) {
		super.delete(employeeId);
	}
	
	@Override
	public void update(EmployeeMasterParth employeeMasterParth) {
		persist(employeeMasterParth);
	}
	
	@Override
	public List<EmployeeMasterParth> getEmployeeMasterList() {
		return findAll();
	}
	
	@Override
	public void create(EmployeeMasterParth employeeMasterParth) {
		save(employeeMasterParth);
	}

}
