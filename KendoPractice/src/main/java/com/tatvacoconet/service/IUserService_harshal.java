package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.UserMaster_harshal;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IUserService_harshal {
	
	public void createUser_harshal(UserMaster_harshal user);
	
	public void updateUser_harshal(UserMaster_harshal user);
	
	public void deleteUser_harshal(Integer userId);
	
	public List<UserMaster_harshal> findAllUsers_harshal();
	
	public UserMaster_harshal findUserById_harshal(Integer userId);
	
	@SuppressWarnings("rawtypes")
	public List getUserCountPerCity_harshal(String columnName);
}
