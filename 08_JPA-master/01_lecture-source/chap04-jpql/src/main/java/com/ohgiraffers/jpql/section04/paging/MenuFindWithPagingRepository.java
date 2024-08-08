package com.ohgiraffers.jpql.section04.paging;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuFindWithPagingRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m From Section04Menu m ORDER BY m.menuCode DESC")
    List<Menu> findAllMenuWithPaging(Pageable pageable);
}
