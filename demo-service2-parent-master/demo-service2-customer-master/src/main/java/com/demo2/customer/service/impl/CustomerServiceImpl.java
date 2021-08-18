/* 
 * Created by 2019年1月13日
 */
package com.demo2.customer.service.impl;

import com.mars.support.dao.BasicDao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import com.demo2.customer.entity.Address;
import com.demo2.customer.entity.Customer;
import com.demo2.customer.service.CustomerService;
import com.mars.support.service.impl.QueryServiceImpl;

/**
 * The implement of the customer service
 * @author fangang
 */
public class CustomerServiceImpl implements CustomerService {

	private BasicDao dao;
	/**
	 * @return the dao
	 */
	public BasicDao getDao() {
		return dao;
	}
	/**
	 * @param dao the dao to set
	 */
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}
	@Override
	public void save(Customer customer) {
		dao.insertOrUpdate(customer);
	}
	@Override
	public void delete(long id) {
		Customer customer = dao.load(id, new Customer());
		dao.delete(customer);
	}
	@Override
	public Customer load(long id) {
		Customer customer = new Customer();
		customer.setId(id);
		return dao.load(id, customer);
	}
	@Override
	public Address loadAddress(long id) {
		Address address = new Address();
		address.setId(id);
		return dao.load(id, address);
	}
	@Override
	public List<Customer> loadMore(List<Long> ids) {
		return dao.loadForList(ids, new Customer());
	}
	@Override
	public List<Address> loadAddresses(List<Long> ids) {
		return dao.loadForList(ids, new Address());
	}

	public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
		Customer customer = new Customer();
		customer.setBirthday(new Date());
		Field[] fields = customer.getClass().getDeclaredFields();
		for (Field field : fields){
//			System.out.println(field.getGenericType().getTypeName());
			String typeName = field.getGenericType().getTypeName();
			if (typeName.equals("java.util.Date")){
				Field dateAttribute = customer.getClass().getDeclaredField(field.getName());
				String methodName = getMethodName(dateAttribute.getName());
				Class<?> clazz = Customer.class;
				Date invoke = (Date) clazz.getDeclaredMethod(methodName).invoke(customer);
				System.out.println(invoke);
			}
		}


	}

	private static String getMethodName(String fieldName) {
		return "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
	}
}
