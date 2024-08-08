package com.ohgiraffers.jpql.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager manager;

    public String findMenuNameByMenuCode(int menuCode) {

        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = :menuCode";
        TypedQuery<String> query = manager.createQuery(jpql, String.class);
        query.setParameter("menuCode", menuCode);

        return query.getSingleResult();
    }

    public Object findObjectByMenuCode(int menuCode) {

        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = :menuCode";
        Query query = manager.createQuery(jpql);
        query.setParameter("menuCode", menuCode);

        return query.getSingleResult();
    }

    public List<Menu> findAllMenusWithTypeQuery() {

        String jpql = "SELECT m FROM Section01Menu as m";
        TypedQuery<Menu> query = manager.createQuery(jpql, Menu.class);     //한 행의 결과 타입 기술

        return query.getResultList();
    }

    public List<Menu> findAllMenusWithQuery() {

        String jpql = "SELECT m FROM Section01Menu as m";
        Query query = manager.createQuery(jpql);

        return query.getResultList();
    }

    public List<Integer> findAllCategoryCodeInMenu() {

        String jqpl = "SELECT DISTINCT m.categoryCode FROM Section01Menu as m";
        TypedQuery<Integer> query = manager.createQuery(jqpl, Integer.class);

        return query.getResultList();
    }

    public List<Menu> findMenusInCategoryCodes(List<Integer> categoryCodes) {

        StringBuilder jqpl = new StringBuilder();
        jqpl.append("SELECT m FROM Section01Menu m WHERE m.categoryCode in (");
        int categoryCodeSize = categoryCodes.size();
        for(int i = 0; i < categoryCodeSize; i++) {
            jqpl.append(categoryCodes.get(i));
            if(i != categoryCodeSize - 1) {
                jqpl.append(",");
            }
        }
        jqpl.append(")");

        TypedQuery<Menu> query = manager.createQuery(jqpl.toString(), Menu.class);

        return query.getResultList();
    }

    public List<Menu> searchMenusBySearchValue(String searchValue) {

        String jpql = "SELECT m FROM Section01Menu m WHERE m.menuName LIKE CONCAT('%', :searchValue, '%')";

        return manager.createQuery(jpql, Menu.class)
                .setParameter("searchValue", searchValue)
                .getResultList();
    }
}
