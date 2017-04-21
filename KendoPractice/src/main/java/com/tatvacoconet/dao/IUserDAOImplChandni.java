package com.tatvacoconet.dao;

import java.util.List;

import com.tatvacoconet.entity.UserMasterChandni;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IUserDAOImplChandni {
	public List<UserMasterChandni> getUserList();
	public void deleteUser(Integer userId);
	public void createUser(UserMasterChandni user);
	public void updateUser(UserMasterChandni user);
	public UserMasterChandni findEntity(Integer userId);
	public UserMasterChandni findUserByEmail(String email);
	@SuppressWarnings("rawtypes")
	public List getUsersByCity(String columnName);
}
