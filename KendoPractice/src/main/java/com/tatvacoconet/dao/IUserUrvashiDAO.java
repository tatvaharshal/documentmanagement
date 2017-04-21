package com.tatvacoconet.dao;

import java.util.List;

import com.tatvacoconet.entity.UserUrvashi;

public interface IUserUrvashiDAO {
	public void saveUser(UserUrvashi user);
	
	public List<UserUrvashi> listUser();

	public void deleteUser(int id);
	
	public void	updateUser(UserUrvashi entity);
	
	public UserUrvashi getUserById(int id); 
}


