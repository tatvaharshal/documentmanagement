package com.tatvacoconet.dao;

import java.util.List;

/**
 * 
 * @author TatvaSoft
 * 
 */
public interface ITatvaSoftDAO<T,ID> {
    
	public void save(T t);
	
    public void persist(T t);

    public T find(ID key);
    
    public List<T> findAll();
    
    public void delete(ID key);
    
    public T findbyName(String col,String name);
    
    @SuppressWarnings("rawtypes")
	public List getCountPerCol(String columnName);
}