package com.tatvacoconet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tatvacoconet.dao.IUserDAOImplChandni;
import com.tatvacoconet.entity.UserMasterChandni;


/**
 * 
 * @author TatvaSoft
 *
 */
@Service
@Transactional
public class UserChandniService implements IUserChandniService
{
	@Autowired
	private IUserDAOImplChandni userDAO;
	
	@Override
	public List<UserMasterChandni> getUserList() {
		return userDAO.getUserList();
	}
	
	@Override
	public void deleteUser(Integer userId) {
		userDAO.deleteUser(userId);
	}

	@Override
	public void createUser(UserMasterChandni user) {
		userDAO.createUser(user);
		}
	
	@Override
	public void updateUser(UserMasterChandni user) {
		userDAO.updateUser(user);
	}
	
	@Override
	public UserMasterChandni find(Integer userId) {
		return userDAO.findEntity(userId);
	}
	
	@Override
	public UserMasterChandni findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getUsersByCity(String columnName) {
		return userDAO.getUsersByCity(columnName);
	}
}
