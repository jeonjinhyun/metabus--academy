package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {

    public List<MenuDTO> findAllMenus(SqlSession sqlSession){
        return sqlSession.selectList("MenuMapper.findAllMenus");
    }

    public static MenuDTO findMenuByMenuCode(SqlSession sqlSession, int menuCode) {
        return sqlSession.selectOne("MenuMapper.findMenuByMenuCode", menuCode);
    }

    public static int registMenu(SqlSession sqlSession, MenuDTO menu) {
        return sqlSession.insert("MenuMapper.registMenu", menu);
    }

    public static int modifyMenu(SqlSession sqlSession, MenuDTO menu) {
        return sqlSession.update("MenuMapper.modifyMenu", menu);
    }

    public static int removeMenu(SqlSession sqlSession, int menuCode) {
        return sqlSession.delete("MenuMapper.removeMenu", menuCode);
    }
}
