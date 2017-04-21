package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.UserMasterChirag;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IUserChiragService {
	
	public void saveUser(UserMasterChirag user);
	
	public void updateUser(UserMasterChirag user);
	
	public void deleteUser(Long userid);
	
	public List<UserMasterChirag> findAll();
	
	public UserMasterChirag findbyId(Long userid);
	
	public boolean isUserExist(UserMasterChirag userMaster);

	@SuppressWarnings("rawtypes")
	public List getCharts();	
	
}
