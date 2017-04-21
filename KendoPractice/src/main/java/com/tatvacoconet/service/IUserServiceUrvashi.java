package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.UserUrvashi;

/**
 * 
 * @author TatvaSoft
 *
 */
public interface IUserServiceUrvashi {
	
	public void save(UserUrvashi user);

	public List<UserUrvashi> listUser();
	
	public void deleteUser(int id);
	
	public void	updateUser(UserUrvashi entity);
	
	public UserUrvashi getUserById(int id); 
		
}
