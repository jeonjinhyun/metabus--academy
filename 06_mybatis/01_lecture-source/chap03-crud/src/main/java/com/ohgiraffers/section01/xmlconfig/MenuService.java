package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

//Transection을 제어합니다.
public class MenuService {
    private final MenuDAO menuDAO;
    public MenuService(){
        menuDAO = new MenuDAO();
    }
    public List<MenuDTO> findAllMenus(){

        SqlSession sqlSession = getSqlSession();

        List<MenuDTO> menuList = menuDAO.findAllMenus(sqlSession);

        sqlSession.close();

        return menuList;
    }

    public MenuDTO findMenuByMenuCode(int menuCode){

        SqlSession sqlSession = getSqlSession();

        MenuDTO menu = MenuDAO.findMenuByMenuCode(sqlSession,menuCode);

        sqlSession.close();

        return menu;
    }

    public boolean registMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = MenuDAO.registMenu(sqlSession,menu);

        if(result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result>0;
    }

    public boolean modifyMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = MenuDAO.modifyMenu(sqlSession,menu);

        if(result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result>0;
    }

    public boolean removeMenuByMenuCode(int menuCode) {
        SqlSession sqlSession = getSqlSession();

        int result = MenuDAO.removeMenu(sqlSession,menuCode);

        if(result>0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result>0;
    }
}
