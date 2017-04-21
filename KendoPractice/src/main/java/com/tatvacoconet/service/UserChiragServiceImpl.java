package com.tatvacoconet.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatvacoconet.dao.IUserChiragDAO;
import com.tatvacoconet.entity.UserMasterChirag;

/**
 * 
 * @author TatvaSoft
 *
 */
@Service
@Transactional
public class UserChiragServiceImpl implements IUserChiragService {

	@Autowired
	private IUserChiragDAO userDAO;
		
	@Override
	public void saveUser(UserMasterChirag user) {
		userDAO.saveUser(user);
	}

	@Override
	public void updateUser(UserMasterChirag user) {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(Long userid) {
		userDAO.deleteUser(userid);
	}

	@Override
	public List<UserMasterChirag> findAll() {
		return userDAO.findAll();
	}

	@Override
	public UserMasterChirag findbyId(Long userId) {
		return userDAO.findbyId(userId);
	}

	@Override
	public boolean isUserExist(UserMasterChirag userMaster) {
		return userDAO.isUserExist(userMaster);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getCharts() {
		return userDAO.getCharts();
	}
	
}
