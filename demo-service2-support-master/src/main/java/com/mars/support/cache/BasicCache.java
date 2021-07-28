/* 
 * create by 2020年1月30日 上午11:28:38
 */
package com.mars.support.cache;

import java.io.Serializable;
import java.util.Collection;

import com.mars.support.entity.Entity;
import com.mars.support.repository.Repository;

/**
 * The basic cache used by ddd repository.
 * @see Repository
 * @author fangang
 */
public interface BasicCache {
	/**
	 * @param vo the value object
	 */
	public <S extends Serializable, T extends Entity<S>> void set(T vo);
	/**
	 * @param id
	 * @param template
	 * @return the value object
	 */
	public <S extends Serializable, T extends Entity<S>> T get(S id, T template);
	/**
	 * @param id
	 * @param template
	 */
	public <S extends Serializable, T extends Entity<S>> void delete(S id, T template);
	/**
	 * @param vos the list of value objects.
	 */
	public <S extends Serializable, T extends Entity<S>> void setForList(Collection<T> vos);
	/**
	 * @param ids the list of id
	 * @param template
	 * @return the list of value objects
	 */
	public <S extends Serializable, T extends Entity<S>> Collection<T> getForList(Collection<S> ids, T template);
	/**
	 * @param ids the list of id
	 * @param template
	 */
	public <S extends Serializable, T extends Entity<S>> void deleteForList(Collection<S> ids, T template);
}
