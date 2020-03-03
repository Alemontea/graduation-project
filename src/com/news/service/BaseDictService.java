package com.news.service;

import java.util.List;

import com.news.pojo.BaseDict;

public interface BaseDictService {

	List<BaseDict> findListById(String id);
}
