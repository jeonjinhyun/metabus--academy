package com.ohgiraffers.association.section01.manytoone;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_menu")
public class Menu {

    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="CATEGORY_CODE")
    private Category category;
    private String orderableStatus;

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
