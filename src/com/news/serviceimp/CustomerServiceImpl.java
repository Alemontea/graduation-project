package com.news.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.annon.LogAnno;
import com.news.dao.CustomerInterface;
import com.news.pojo.Customer;
import com.news.pojo.QueryVo;
import com.news.service.CustomerService;
import com.news.util.Page;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerInterface customerInterface;
	
	@Override
	public Page<Customer> findCustomerList(QueryVo queryVo) {
		
		if(queryVo.getPage()!=null){
			queryVo.setStart((queryVo.getPage()-1)*queryVo.getSize());
		}
		Page<Customer> pc = new Page<Customer>();
		pc.setRows(customerInterface.findListCustomer(queryVo));
		pc.setTotal(customerInterface.findListCustomerCounts(queryVo));
		pc.setPage(queryVo.getPage());
		pc.setSize(queryVo.getSize());
		return pc;
	}

	@Override
	public Customer findOneCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerInterface.findOneCustomerById(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
      //System.out.println("--------555---------");

		customerInterface.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(long customer) {
		// TODO Auto-generated method stub
		customerInterface.deleteCustomer(customer);
	}

	

}
