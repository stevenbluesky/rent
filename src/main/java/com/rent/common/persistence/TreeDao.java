/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.common.persistence;

import java.util.List;

/**
 * DAO支持类实
 * @author ThinkGem
 * @version 2014-05-16
 * @param <T>
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

	/**
	 * 找到有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}