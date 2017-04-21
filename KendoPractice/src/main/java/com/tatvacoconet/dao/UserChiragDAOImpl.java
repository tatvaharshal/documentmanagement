package com.tatvacoconet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tatvacoconet.entity.UserMasterChirag;

/**
 * @author TatvaSoft
 *
 */
@Repository
public class UserChiragDAOImpl extends TatvaSoftDAOImpl<UserMasterChirag, Long> implements IUserChiragDAO {
  
 	public UserChiragDAOImpl() {
		super(UserMasterChirag.class);
	}


	@Override
	public void saveUser(UserMasterChirag user) {
		save(user);
	}


	@Override
	public void updateUser(UserMasterChirag user) {
		persist(user);
	}


	@Override
	public void deleteUser(Long userid) {
		delete(userid);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMasterChirag> findAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(UserMasterChirag.class);
		return cr.list();
	}


	@Override
	public UserMasterChirag findbyId(Long userId) {
		return find(userId);
	}
	
	
	@Override
	public boolean isUserExist(UserMasterChirag userMaster) {
		boolean isFound = false;
		UserMasterChirag userMasterChirag = findbyName("fname", userMaster.getFname());
		if( userMasterChirag != null)
			isFound = true;
		
		return isFound;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getCharts() {
		List searchedList = new ArrayList<>();
		
		try{
			
			searchedList = getCountPerCol("city");
		}catch(Exception e){
			//logger.error("Error from getUserCountPerCity_harshal : " + e.getMessage());
		}
		return searchedList;
	}
  
}