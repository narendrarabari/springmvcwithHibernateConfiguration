package com.pmc.dao;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

	T findOne(final Serializable id);

	List<T> findAll();

	void create(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final Serializable entityId);

	T findSingleByQuery(String query);

	List<T> findAllByQuery(String query);
	
	void saveOrUpdate(final T entity);
	 
	public List< T > findByExample( T instance );

}
