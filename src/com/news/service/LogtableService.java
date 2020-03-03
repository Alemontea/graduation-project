package com.news.service;

import java.sql.SQLException;

import com.news.pojo.Logtable;

public interface LogtableService {

    /**
     * 增加日志
     * @param log
     * @return
     * @throws SQLException
     */
    public boolean addLog(Logtable log) throws SQLException;
}
