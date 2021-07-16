/* 
 * Created by 2019年1月30日
 */
package com.demo2.supplier.service;

import java.util.List;

import com.demo2.supplier.entity.Supplier;

/**
 * The service of suppliers.
 * @author fangang
 */
public interface SupplierService {
	/**
	 * @param id
	 * @return the supplier
	 */
	public Supplier loadSupplier(Long id);
	/**
	 * @param ids
	 * @return
	 */
	public List<Supplier> loadSuppliers(List<Long> ids);
	
	/**
	 * @return the list of supplier
	 */
	public List<Supplier> listOfSuppliers();
}
