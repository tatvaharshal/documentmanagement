package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.UserMasterChandni;

public interface IUserChandniService {
	public List<UserMasterChandni> getUserList();
	public void deleteUser(Integer userId);
	public void createUser(UserMasterChandni user);
	public void updateUser(UserMasterChandni user);
	public UserMasterChandni find(Integer userId);
	public UserMasterChandni findUserByEmail(String email);
	@SuppressWarnings("rawtypes")
	public List getUsersByCity(String columnName);
}
