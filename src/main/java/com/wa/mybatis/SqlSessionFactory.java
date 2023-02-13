package com.wa.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

/**
 * SqlSessionFactory
 * 2023/2/13 7:40 下午
 *
 * @author wuao
 */
public class SqlSessionFactory {

    public static SqlSession getSqlSession() {
        return new DefaultSqlSession(null, null);
    }
}
