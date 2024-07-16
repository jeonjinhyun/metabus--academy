package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {
    private static SqlSessionFactory sqlSessiontFactory;

    public static SqlSession getSqlSession() {
        if(sqlSessiontFactory == null) {
            String resource = "mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessiontFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SqlSession sqlSession = sqlSessiontFactory.openSession(false);

        System.out.println("sqlSessionFactory의 hashCode : "+sqlSessiontFactory.hashCode());
        System.out.println("sqlSession의 hashCode : "+sqlSession.hashCode());

        return sqlSession;
    }
}
