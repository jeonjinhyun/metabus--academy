package com.ohgiraffers.jpql.section06.join;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinRepository extends JpaRepository<Menu, Integer> {


    @Query("SELECT m FROM Section06Menu m JOIN m.category c")
    List<Menu> findAllMenusUsingInnerJoin();

    @Query("SELECT m FROM Section06Menu m JOIN FETCH m.category c")
    List<Menu> findAllMenusUsingFetchJoin();

    @Query("SELECT DISTINCT c FROM Section06Category c LEFT JOIN c.menuList m")
    List<Category> findAllMenusInCategory();
}
