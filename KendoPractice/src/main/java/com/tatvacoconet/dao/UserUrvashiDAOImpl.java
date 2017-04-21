package com.tatvacoconet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tatvacoconet.entity.UserUrvashi;

@Repository
public class UserUrvashiDAOImpl extends TatvaSoftDAOImpl<UserUrvashi, Integer>implements IUserUrvashiDAO{

	public UserUrvashiDAOImpl() {
		super(UserUrvashi.class);
	}

	@Override
	public void saveUser(UserUrvashi user) {
		save(user);
		
	}

	@Override
	public List<UserUrvashi> listUser() {
		// TODO Auto-generated method stub
		return findAll();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		delete(id);
	}

	@Override
	public void updateUser(UserUrvashi entity) {
		persist(entity);
	}

	@Override
	public UserUrvashi getUserById(int id) {
		// TODO Auto-generated method stub
		return find(id);
	}
	
	

}
