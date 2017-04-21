package com.tatvacoconet.dao;

import java.util.List;

import com.tatvacoconet.entity.DashboardWidgetMaster;
import com.tatvacoconet.entity.UserMasterChirag;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IUserChiragDAO {
	
public void saveUser(UserMasterChirag user);
	
	public void updateUser(UserMasterChirag user);
	
	public void deleteUser(Long userid);
	
	public List<UserMasterChirag> findAll();
	
	public UserMasterChirag findbyId(Long userid);
	
	public boolean isUserExist(UserMasterChirag userMaster);
	
	@SuppressWarnings("rawtypes")
	public List getCharts();
}

