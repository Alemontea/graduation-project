package com.news.serviceimp;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.news.dao.LogtableMapper;
import com.news.pojo.Logtable;
import com.news.service.LogtableService;
import com.sun.org.apache.regexp.internal.recompile;
@Service
public class LogtableServiceImpl implements LogtableService{

	 @Autowired
	    private LogtableMapper logtableMapper;
	    @Override
	    public boolean addLog(Logtable log) throws SQLException {
	    	boolean a = logtableMapper.insert(log) > 0 ? true : false;
	    	return a ;
	    }

}
