package com.pmc.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;



@SuppressWarnings("unchecked")
public abstract class AbstractDao<T extends Serializable> implements
		IOperations<T> {
	private Class<T> entity;
	
	private Log log = LogFactory.getLog( AbstractDao.class );

	@Autowired
	private SessionFactory sessionFactory;

	// API

	protected final void setClass(final Class<T> entityToSet) {
		entity = entityToSet;
	}

	public final T findOne(final Serializable id) {
		return ((T) getCurrentSession().get(entity, id));
	}

	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + entity.getName())
				.list();
	}

	@Override
	public final void create(final T entity) {
		Assert.notNull(entity);
		getCurrentSession().persist(entity);
	}
	
	@Override
	public final void saveOrUpdate(final T entity){
		Assert.notNull(entity);
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public final T update(final T entity) {
		Assert.notNull(entity);
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public final void delete(final T entity) {
		Assert.notNull(entity);
		getCurrentSession().delete(entity);
	}

	@Override
	public final void deleteById(final Serializable entityId) {
		final T entity = findOne(entityId);
		Assert.notNull(entity);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public final T findSingleByQuery(String query) {
		List<T> resultList = getCurrentSession().createQuery(query).list();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public final List<T> findAllByQuery(String query) {
		return getCurrentSession().createQuery(query).list();
	}
	
	public List< T > findByExample( T instance )
	{
		log.debug( "finding bo instance by example" );
		
		List< T > results = ( List< T > ) getCurrentSession().createCriteria( instance.getClass() )
													  .add(  Example.create(instance)).list();
		log.debug( "find by example successful, result size: " + results.size() );
		return results;
	}
	
	
}
