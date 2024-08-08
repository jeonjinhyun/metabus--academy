package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class LikeCompositeKey {

    @Embedded
    private LikedMemberNo menberNo;

    @Embedded
    private LikedBookNo bookNo;

    protected LikeCompositeKey() {}

    public LikeCompositeKey(LikedMemberNo menberNo, LikedBookNo bookNo) {
        this.menberNo = menberNo;
        this.bookNo = bookNo;
    }

    public LikedMemberNo getMenberNo() {
        return menberNo;
    }

    public LikedBookNo getBookNo() {
        return bookNo;
    }

    @Override
    public String toString() {
        return "LikeCompositeKey{" +
                "menberNo=" + menberNo +
                ", bookNo=" + bookNo +
                '}';
    }
}
