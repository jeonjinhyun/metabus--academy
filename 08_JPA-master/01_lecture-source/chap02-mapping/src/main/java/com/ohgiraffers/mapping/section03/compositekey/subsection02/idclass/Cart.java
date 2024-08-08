package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_cart")
@IdClass(CompositeKey.class)
public class Cart {

    // 기본키에서 사용 가능한 타입만 사용할 수 있다.
    @Id
    @Column(name="CART_OWNER")
    private int cartOwner;

    @Id
    @Column(name="ADDED_BOOK")
    private int addedBook;

    @Column(name="quantity")
    private int quantity;

    protected Cart() {}

    public Cart(int cartOwner, int addedBook, int quantity) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
        this.quantity = quantity;
    }

    public int getCartOwner() {
        return cartOwner;
    }

    public int getAddedBook() {
        return addedBook;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartOwner=" + cartOwner +
                ", addedBook=" + addedBook +
                ", quantity=" + quantity +
                '}';
    }
}
