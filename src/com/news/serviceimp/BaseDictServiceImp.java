package com.news.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dao.BaseDiceInterface;
import com.news.pojo.BaseDict;
import com.news.service.BaseDictService;

@Service
public class BaseDictServiceImp implements BaseDictService {

	@Autowired
	private BaseDiceInterface baseDiceInterfaceMapper;
	
	@Override
	public List<BaseDict> findListById(String id) {
		// TODO Auto-generated method stub
		return baseDiceInterfaceMapper.findListById(id);
	}

}
