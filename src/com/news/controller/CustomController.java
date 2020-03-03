package com.news.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.news.annon.LogAnno;
import com.news.pojo.BaseDict;
import com.news.pojo.Customer;
import com.news.pojo.QueryVo;
import com.news.service.BaseDictService;
import com.news.service.CustomerService;
import com.news.serviceimp.CustomerServiceImpl;
import com.news.util.FileUploadUtil;
import com.news.util.Page;


/**
 * 
 * @author ZHM
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomController {
 
	@Autowired
	private  BaseDictService  baseDictService;
	
	@Autowired
	private  CustomerService  customerService;
	
	@Value("${customer.from.type}")
	private String cunsCode;
	@Value("${customer.industry.type}")
	private String cunsCode2;
	@Value("${customer.level.type}")
	private String cunsCode3;
	
	@RequestMapping("/list")
	@LogAnno(operationType="查询操作:",operationName="查询用户")  
	public String ShowList2(QueryVo queryVo,Model model) throws UnsupportedEncodingException{
		
		if(org.apache.commons.lang3.StringUtils.isNotBlank(queryVo.getCustName())){
			queryVo.setCustName(new String(queryVo.getCustName().getBytes("iso8859-1"),"utf-8"));
		}
		
		List<BaseDict> findListById = baseDictService.findListById(cunsCode);
		List<BaseDict> findListById2 = baseDictService.findListById(cunsCode2);
		List<BaseDict> findListById3 = baseDictService.findListById(cunsCode3);
		Page<Customer> findCustomerList = customerService.findCustomerList(queryVo);
		model.addAttribute("fromType", findListById);
		model.addAttribute("industryType", findListById2);
		model.addAttribute("levelType", findListById3);
		model.addAttribute("page", findCustomerList);
		
	
		model.addAttribute("custName",queryVo.getCustName());
		
		model.addAttribute("custSource",queryVo.getCustSource());
		model.addAttribute("custLevel",queryVo.getCustLevel());		
		model.addAttribute("custIndustory",queryVo.getCustIndustory());

		
		return "customer" ;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	@LogAnno(operationType="update操作:",operationName="修改用户")
	public Customer edit(int id){
		Customer findOneCustomerById = customerService.findOneCustomerById(id);
		return findOneCustomerById ;
	}
	
	@RequestMapping("/update1")
	@ResponseBody
	@LogAnno(operationType="update操作:",operationName="修改用户")
	public String update(Customer customer){
		customerService.updateCustomer(customer);
		return "success";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@LogAnno(operationType="三处操作:",operationName="修改用户")  
	public String delete(long id){
		customerService.deleteCustomer(id);
		System.out.println("xiugai");
		return "ok";
	}
	
	 @RequestMapping(value="/upload1",method=RequestMethod.GET)    
	    public String uploaW(){ 
	    	//FileUploadUtil.upload(file, request);
	    	return "up";
	    }
	
	
	
    @RequestMapping(value="/upload",method=RequestMethod.POST)  
    @ResponseBody  
    public String upload(MultipartFile file,HttpServletRequest request){ 
    
    	FileUploadUtil.upload(file, request);
    	return "ok";
    }
    
    @RequestMapping(value="/down",method=RequestMethod.GET)   
    public String dwon(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
    
    	FileUploadUtil.down(request, response, "file", "2ecf26947b1d0c7b4d16e0d571585db2.jpg");
    	return "ok";
    }
    
}
