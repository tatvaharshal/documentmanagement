package com.tatvacoconet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tatvacoconet.entity.UserMaster_harshal;

/**
 * @author TatvaSoft
 *
 */
@Repository
public class UserDAOImpl_harshal extends TatvaSoftDAOImpl<UserMaster_harshal, Integer> implements IUserDAO_harshal {
  
	private Logger logger = LoggerFactory.getLogger(UserDAOImpl_harshal.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
 	public UserDAOImpl_harshal() {
		super(UserMaster_harshal.class);
	}


	@Override
	public void createUser_harshal(UserMaster_harshal user) {
		save(user);
	}


	@Override
	public void updateUser_harshal(UserMaster_harshal user) {
		persist(user);
	}


	@Override
	public void deleteUser_harshal(Integer userId) {
		delete(userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMaster_harshal> findAllUsers_harshal() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(UserMaster_harshal.class);
		return cr.list();
	}


	@Override
	public UserMaster_harshal findUserById_harshal(Integer userId) {
		return find(userId);
	}


	@SuppressWarnings({ "rawtypes" })
	@Override
	public List getUserCountPerCity_harshal(String columnName) {
		return getCountPerCol(columnName);
	}
	  
}