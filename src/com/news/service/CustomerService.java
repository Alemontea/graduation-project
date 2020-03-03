package com.news.service;

import com.news.pojo.Customer;
import com.news.pojo.QueryVo;
import com.news.util.Page;

public interface CustomerService {

	Page<Customer> findCustomerList(QueryVo queryVo);
	Customer findOneCustomerById(int id);
	void updateCustomer(Customer customer);
	void deleteCustomer(long customer);
}
