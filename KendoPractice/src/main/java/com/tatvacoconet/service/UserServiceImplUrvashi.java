package com.tatvacoconet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatvacoconet.dao.IUserUrvashiDAO;
import com.tatvacoconet.entity.UserUrvashi;

/**
 * 
 * @author TatvaSoft
 *
 */
@Service
@Transactional
public class UserServiceImplUrvashi implements IUserServiceUrvashi {

	@Autowired
	private IUserUrvashiDAO userDAO;

	@Override
	public void save(UserUrvashi user) {
		userDAO.saveUser(user);
		
	}

	@Override
	public List<UserUrvashi> listUser() {
		// TODO Auto-generated method stub
		return userDAO.listUser();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
	}

	@Override
	public void updateUser(UserUrvashi entity) {
		userDAO.updateUser(entity);
	}

	@Override
	public UserUrvashi getUserById(int id) {
		// TODO Auto-generated method stub
		return userDAO.getUserById(id);
	}
	
	


}
