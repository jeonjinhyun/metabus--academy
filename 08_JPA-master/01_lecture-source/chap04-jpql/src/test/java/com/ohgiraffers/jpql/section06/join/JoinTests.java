package com.ohgiraffers.jpql.section06.join;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class JoinTests {

    /* 조인의 종류
    * 1. 일반 조인 : 일반적인 SQL 조인을 의미 (내부조인, 외부조인, 컬렉션조인, 세타조인)
    * 2. 페치 조인 : JPQL에서 성능 최적화를 위해 제공하는 기능으로 연관된 엔티티나 컬렉션을 한 번에 조회할 수 있다.
    *               지연 로딩이 아닌 즉시 로딩을 수행하며 join fetch 명령어를 사용한다.
    * */

    @Autowired
    private JoinService joinService;

    @DisplayName("내부 조인을 이용한 조회 테스트")
    @Test
    void testFindAllMenusUsingInnerJoin() {

        // Menu 엔티티에 대한 조회만 일어나고 Category엔티티에 대한 조회는 나중에 필요할 때 일어나게 된다. (N+1문제 발생함)
        // 지연 로딩 때문에 발생하게 됨
        // fetchType을 EAGER로 설정해도 동일한 문제가 발생한다. (fetch join으로 해결해야 함, 혹은 dto 프로젝션 활용)
        // SELECT의 대상은 영속화 하여 가져오지만 조인의 대상은 영속화 하지 않는다.
        Assertions.assertDoesNotThrow(
                () -> {
                    List<Menu> menus = joinService.findAllMenusUsingInnerJoin();
                    menus.forEach(System.out::println);
                }
        );
    }

    @DisplayName("페치 조인을 이용한 조회 테스트")
    @Test
    void testFindAllMenusUsingFetchJoin() {

        // 페치 조인을 하면 처음 SQL 실행 후 로딩할 때 조인 결과를 다 조회한 뒤에 사용하는 방식이기 때문에
        // 쿼리 실행 횟수가 줄어들게 된다. (대부분의 경우 성능이 향상된다)
        // 하지만 페치 조인을 사용하는 경우 paging을 하면 안된다.
        // paging을 시도하는 경우 Memory가 과하게 사용되어 장애의 요인이 될 수 있다.
        Assertions.assertDoesNotThrow(
                () -> {
                    List<Menu> menus = joinService.findAllMenusUsingFetchJoin();
                    menus.forEach(System.out::println);
                }
        );
    }

    @DisplayName("외부 조인을 이용한 조회 테스트")
    @Test
    @Transactional
    void testFindAllMenusInCategory() {

        // 컬렉션 조인
        // 컬렉션 조인은 의미상으로 분류된 것으로 컬렉션을 지니고 있는 엔티티를 기준으로 조인하는 것을 말한다.
        // 카테고리를 조회하고 그 카테고리의 메뉴 목록을 조회한 후
        // 해당 메뉴들의 카테고리를 기준으로 다시 카테고리를 조회하기 때문에 중복된 데이터가 조회된다.
        // DISTINCT 키워드를 사용해서 중복된 값을 제거해서 사용해야 한다. (하이버네이트 5까지)
        Assertions.assertDoesNotThrow(
                () -> {
                    List<Category> categories = joinService.findAllMenusInCategory();
                    categories.forEach(category -> {
                        System.out.println(category);
                        category.getMenuList().forEach(System.out::println);
                    });
                }
        );
    }
}
