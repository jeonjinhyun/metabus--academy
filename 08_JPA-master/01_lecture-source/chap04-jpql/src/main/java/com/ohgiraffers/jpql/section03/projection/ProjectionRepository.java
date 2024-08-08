package com.ohgiraffers.jpql.section03.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectionRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Section03Menu m")
    List<Menu> findAllMenusWithEntityProjection();

    @Query("SELECT c.categoryName FROM Section03Category c")
    List<CategoryName> findAllCategoriesWithEmbeddedTypeProjection();

    @Query("SELECT c.categoryCode FROM Section03Category c")
    List<Integer> findAllCategoriesWithScalarProjection();

    @Query("SELECT c.categoryCode, c.categoryName FROM Section03Category c")
    List<Object[]> findAllCategoryCodesAndNames();

    @Query("SELECT new com.ohgiraffers.jpql.section03.projection.CategoryInfo(c.categoryCode, c.categoryName) FROM Section03Category c")
    List<CategoryInfo> findAllCategoryNameWithNew();
}
