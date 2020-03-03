package com.news.dao;

import java.util.List;

import com.news.pojo.Customer;
import com.news.pojo.QueryVo;

public interface CustomerInterface {

	List<Customer> findListCustomer(QueryVo queryVo);
	
	Integer findListCustomerCounts(QueryVo queryVo);
	
	Customer findOneCustomerById(int id);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(long customer);
}
