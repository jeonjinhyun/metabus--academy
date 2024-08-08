package com.ohgiraffers.jpql.section05.groupfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class GroupFunctionTests {

    /* JPQL의 그룹함수는 COUNT, MAX, MIN, SUM, AVG로 SQL의 그룹함수와 별반 차이가 없다.
    *  단, 몇 가지 주의사항이 있다.
    * 1. 그룹함수의 반환 타입은 결과값이 정수이면 Long, 실수이면 Double로 반환된다.
    * 2. 값이 없는 상태에서 COUNT를 제외한 그룹함수는 null이 되고 count만 0이 된다.
    *    따라서 반환 값을 담기 위해 선언하는 변수 타입을 기본자료형으로 하게 되면,
    *    조회 결과를 언박싱 할 떄 NPE가 발생한다.
    * 3. 그룹함수의 반환 자료형은 Long or Double이기 때문에
    *    HAVING절에서 그룹함수 결과값과 비교하기 위한 파라미터 타입은 Long or Double로 해야 한다.
    * */

    @Autowired
    private GroupFunctionService groupFunctionService;

    @DisplayName("특정 카테고리에 등록된 메뉴 수 조회")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void testCountByCategoryCode(int categoryCode) {

        Assertions.assertDoesNotThrow(
                () -> {
                    long countOfMenu = groupFunctionService.countMenuByCategoryCode(categoryCode);
                    System.out.println("countOfMenu = " + countOfMenu);
                }
        );
    }

    @DisplayName("count를 제외한 다른 그룹 함수의 조회 결과가 없는 경우 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void testSumMenuPriceByCategoryCode(int categoryCode) {

        if(categoryCode == 2 || categoryCode == 3) {
            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> {
                        long sumOfPrice = groupFunctionService.sumMenuPriceByCategoryCode(categoryCode);
                    }
            );
        }

        Assertions.assertDoesNotThrow(
                () -> {
                    Long sumOfPrice = groupFunctionService.sumMenuPriceByCategoryCode(categoryCode);
                }
        );
    }

    @DisplayName("GROUP BY절과 HAVING절을 사용한 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {30000, 40000, 50000, 60000})
    void testSumMenuPriceGroupByCategoryCode(long minPrice) {

        Assertions.assertDoesNotThrow(
                () -> {
                    List<Object[]> sumPriceOfCategories = groupFunctionService.sumMenuPriceGroupByCategoryCode(minPrice);
                    sumPriceOfCategories.forEach(
                            row -> Arrays.stream(row).forEach(System.out::println)
                    );
                }
        );
    }
}
