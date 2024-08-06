package com.ohgiraffers.persistence.section03.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.stream.Stream;

public class EntityLifecycleTests {
    /*  엔티티의 생명주기
    *   비영속(new/transient)  : 영속성 컨텍스트와 전혀 관계가 없는 상태
    *   영속(managed) : 영속성 컨텍스트에 저장된 상태
    *   준영속(detached)   : 영속성 컨텍스트에 저장되었다가 분리된 상태
    *   삭제(removed) : 삭제된 상태
    * */
    // 영속성 컨텍스트?
    // 엔티티 매니저가 엔티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.
    // (엔티티 메니저가 생성될 때 하나의 영속성 컨텍스트가 만들어진다.)

    private EntityLifecycle lifecycle;

    @BeforeEach
    void setUp(){
        lifecycle = new EntityLifecycle();
    }

    @DisplayName("비영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void testTransient(int menuCode){
        Menu foundMenu = lifecycle.findMenuByMenuCode(menuCode);

        Menu newMenu = new Menu(
                menuCode,
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );

        Assertions.assertNotEquals(newMenu,foundMenu);
    }

    @DisplayName("다른 엔티티 매니저가 관리하는 엔티티의 영속성 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints ={1,2,3})
    void testManagedOtherEntityManager(int menuCode){
        Menu menu1 = lifecycle.findMenuByMenuCode(menuCode);
        Menu menu2 = lifecycle.findMenuByMenuCode(menuCode);

        Assertions.assertNotEquals(menu1,menu2);
    }

    @DisplayName("같은 엔티티 매니저가 관리하는 엔티티의 영속성 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testManagedSameEntityManager(int menuCode){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu menu1 = manager.find(Menu.class, menuCode);
        Menu menu2 = manager.find(Menu.class, menuCode);

        Assertions.assertEquals(menu1,menu2);
    }

    private static Stream<Arguments> newMenu(){
        return Stream.of(
                Arguments.of("새로운 메뉴", 5000,4,"Y")
        );
    }

    @DisplayName("엔티티 영속성 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    void testNewMenu(String menuName, int menuPrice, int categoryCode, String orderableStatus){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        Menu menu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);

        transaction.begin();
        manager.persist(menu);
        manager.flush();

        Assertions.assertTrue(manager.contains(menu));
        transaction.rollback();
    }

    @DisplayName("엔티티 영속성 추가 테스트")
    @ParameterizedTest
    @CsvSource({"1,메론죽","2,김치딸기죽"})
    void testMangeEntityModify(int menuCode,String menuName){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu foundMenu = manager.find(Menu.class, menuCode);
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        foundMenu.setMenuName(menuName);
        manager.flush();

        Menu expectedMenu = manager.find(Menu.class,menuCode);
        Assertions.assertEquals(expectedMenu,foundMenu);
        transaction.rollback();
    }

    @DisplayName("준영속성 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11,1000","12,1000"})
    void testDetachEntity(int menuCode,int menuPrice){

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();
        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);
        manager.flush();

        Assertions.assertNotEquals(foundMenu.getMenuPrice(),manager.find(Menu.class,menuCode).getMenuPrice());
        transaction.rollback();
    }

    @DisplayName("준영속성 detach 후 다시 영속화 테스트")
    @ParameterizedTest(name = "[{index}] 준영속화 된 {0}번 메뉴를 {1}원 으로 변경하고 다시 영속화 되는지 확인")
    @CsvSource({"11,1000","12,1000"})
    void testDetachAndPersist(int menuCode,int menuPrice){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();
        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        manager.merge(foundMenu);
        manager.flush();

        Assertions.assertEquals(foundMenu.getMenuPrice(),manager.find(Menu.class,menuCode).getMenuPrice());
        transaction.rollback();
    }

    @DisplayName("준영속성 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testClearPersistenceContext(int menuCode){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu foundMenu = manager.find(Menu.class, menuCode);
        manager.clear();

        Menu expectedMenu = manager.find(Menu.class,menuCode);
        Assertions.assertNotEquals(expectedMenu,foundMenu);
        Assertions.assertNotEquals(expectedMenu,foundMenu);

    }

    @DisplayName("준영속성 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testClosePersistenceContext(int menuCode){

        // close는 영속성 컨텍스트를 종료시킨다.
        // 종료시킨 이후 다시 영속성 컨텍스트를 사용하려 하면 IllegalStateException이 발생하게 된다.

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu foundMenu = manager.find(Menu.class, menuCode);
        manager.close();

        Assertions.assertThrows(
                IllegalStateException.class,
                ()->manager.find(Menu.class,menuCode)
        );
    }

    @DisplayName("영속성 엔티티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testRemoveEntity(int menuCode) {
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        Menu foundMenu = manager.find(Menu.class, menuCode);
        manager.remove(foundMenu);

        manager.flush();

        Assertions.assertFalse(manager.contains(foundMenu));
        transaction.rollback();
    }
}
