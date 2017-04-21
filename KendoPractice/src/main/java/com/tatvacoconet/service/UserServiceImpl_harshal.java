package com.tatvacoconet.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatvacoconet.dao.IUserDAO_harshal;
import com.tatvacoconet.entity.UserMaster_harshal;

/**
 * 
 * @author TatvaSoft
 *
 */
@Service
@Transactional
public class UserServiceImpl_harshal implements IUserService_harshal {

	@Autowired
	private IUserDAO_harshal userDAO_harshal;
		
	@Override
	public void createUser_harshal(UserMaster_harshal user) {
		userDAO_harshal.createUser_harshal(user);
	}

	@Override
	public void updateUser_harshal(UserMaster_harshal user) {
		userDAO_harshal.updateUser_harshal(user);
	}

	@Override
	public void deleteUser_harshal(Integer userId) {
		userDAO_harshal.deleteUser_harshal(userId);
	}

	@Override
	public List<UserMaster_harshal> findAllUsers_harshal() {
		return userDAO_harshal.findAllUsers_harshal();
	}

	@Override
	public UserMaster_harshal findUserById_harshal(Integer userId) {
		return userDAO_harshal.findUserById_harshal(userId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getUserCountPerCity_harshal(String columnName) {
		return userDAO_harshal.getUserCountPerCity_harshal(columnName);
	}

}
