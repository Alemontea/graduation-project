package com.news.dao;

import java.util.List;

import com.news.pojo.BaseDict;

public interface BaseDiceInterface {

	List<BaseDict> findListById(String id);
}
