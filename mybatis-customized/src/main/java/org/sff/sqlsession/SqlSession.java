package org.sff.sqlsession;

import java.util.List;

/**
 * 一次curd查询会话
 */
public interface SqlSession {

    <T> T selectOne(String statementId, Object param);

    <T> List<T> selectList(String statementId, Object param);

}
