package org.sff.sqlsession;


public interface SqlSessionFactory {

    /**
     * 获取会话
     */
    SqlSession openSqlSession();

}
