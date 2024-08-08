package com.ohgiraffers.association.section02.onetomany;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="TBL_ORDER_MENU")
public class OrderMenu {

    @EmbeddedId
    private OrderMenuPK orderMenuPK;

    @Column(name="ORDER_AMOUNT")
    private int orderAmount;

    public OrderMenu() {}

    public OrderMenu(OrderMenuPK orderMenuPK, int orderAmount) {
        this.orderMenuPK = orderMenuPK;
        this.orderAmount = orderAmount;
    }

    public OrderMenuPK getOrderMenuPK() {
        return orderMenuPK;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "orderMenuPK=" + orderMenuPK +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
