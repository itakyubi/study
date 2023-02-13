package com.wa.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

/**
 * Main
 * 2023/2/13 4:50 下午
 *
 * @author wuao
 */
public class Main {
    public static void main(String[] args) {
        /*Configuration configuration = new Configuration();
        Executor executor = new BaseExecutor();

        SqlSession sqlSession = new DefaultSqlSession(configuration, executor);
        PerpetualCache cache = new PerpetualCache();*/

    }

    public void firstCache() {
        SqlSession sqlSession = new DefaultSqlSession(null, null);

        // 第一次查询没有命中缓存，直接查询数据库
        Object user1 = sqlSession.selectList("select user where xxx");
        // 第二次查询命中一级缓存，不查数据库
        Object user2 = sqlSession.selectList("select user where xxx");
    }

    public void secondCache() {
        SqlSession sqlSession1 = SqlSessionFactory.getSqlSession();
        // 第一次查询没有命中缓存，直接查询数据库
        Object user1 = sqlSession1.selectList("select user where xxx");

        // 跟sqlSession1是同一个factory创建的
        SqlSession sqlSession2 = SqlSessionFactory.getSqlSession();
        // 命中二级缓存，不查数据库
        Object user2 = sqlSession2.selectList("select user where xxx");
    }
}
