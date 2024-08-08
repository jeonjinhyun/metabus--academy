package com.ohgiraffers.datajpa.section03.pagingandsort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class PagingAndSortTests {

    @Autowired
    private PagingAndSortService pagingAndSortService;

    @DisplayName("페이징 하여 메뉴 목록 조회")
    @ParameterizedTest
    @CsvSource({"0,5", "1,5"})
    void testFindAllMenusWithPaging(int offset, int limit) {

        Assertions.assertDoesNotThrow(
                () -> {
                    Page<FoundMenuResponseDTO> page = pagingAndSortService.findAllMenusWithPaging(offset, limit);
                    System.out.println("조회한 내용 목록 : " + page.getContent());
                    System.out.println("총 페이지 수 : " + page.getTotalPages());
                    System.out.println("총 메뉴 수 : " + page.getTotalElements());
                    System.out.println("첫 페이지 여부 : " + page.isFirst());
                    System.out.println("마지막 페이지 여부 : " + page.isLast());
                    System.out.println("정렬 방식 : " + page.getSort());
                }
        );
    }

    @DisplayName("정렬하여 메뉴 목록 조회")
    @ParameterizedTest
    @ValueSource(strings = {"menuName", "menuPrice"})
    void testFindAllMenusOrderBy(String columnName) {

        Assertions.assertDoesNotThrow(
                () -> pagingAndSortService.findAllMenusOrderBy(columnName)
                        .forEach(System.out::println)
        );
    }
}
