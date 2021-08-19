/* 
 * Created by 2019年1月13日
 */
package com.demo2.customer.service.impl;

import com.mars.support.dao.BasicDao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
//		Customer customer = new Customer();
//		customer.setBirthday(new Date());
//		Field[] fields = customer.getClass().getDeclaredFields();
//		for (Field field : fields){
////			System.out.println(field.getGenericType().getTypeName());
//			String typeName = field.getGenericType().getTypeName();
//			if (typeName.equals("java.util.Date")){
//				Field dateAttribute = customer.getClass().getDeclaredField(field.getName());
//				String methodName = getMethodName(dateAttribute.getName());
//				Class<?> clazz = Customer.class;
//				Date invoke = (Date) clazz.getDeclaredMethod(methodName).invoke(customer);
//				System.out.println(invoke);
//			}
//		}
		Customer customer = new Customer();
		customer.setBirthday(LocalDateTime.now());
		LocalDateTime birthday = customer.getBirthday();

//		formatLocalDateTimeForObj(customer);
		formatLocalDateTime(birthday);
		System.out.println(birthday);
//		System.out.println(customer);

	}

	public static void formatLocalDateTimeForObj(Object entity){
		if(entity == null){
			return;
		}
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields.length == 0) {
			return;
		}
		Arrays.stream(fields).forEach(field -> {
			if (!field.getType().isPrimitive()) {
				try {
					Object attr = clazz.getDeclaredMethod(getMethodName(field.getName())).invoke(entity);
					if (attr instanceof LocalDateTime) {
						formatLocalDateTime((LocalDateTime)attr);
					}else {
						formatLocalDateTimeForObj(attr);
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void formatLocalDateTime(LocalDateTime attr) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String format = attr.format(fmt);
		System.out.println(format);
	}

	private static String getMethodName(String fieldName) {
		return "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
	}
}
