package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.ohgiraffers.common.Template.close;

public class MenuDAO {
    // Mybatis 스타일
    public int insertMenu(Connection con, MenuDTO menu){
        PreparedStatement pstmt =null;
        int result=0;

        String query = "INSERT INTO TBL_MENU (MENU_NAME,MENU_PRICE,CATEGORY_CODE,ORDERABLE_STATUS) "
                + "VALUES(?,?,?,?)";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setDouble(2, menu.getMenuPrice());
            pstmt.setInt(3, menu.getCategoryCode());
            pstmt.setString(4, menu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        return result;
    }

    public int updateMenu(Connection con, MenuDTO menu){
        PreparedStatement pstmt =null;
        int result=0;
        String query = "UPDATE TBL_MENU SET MENU_NAME=? WHERE MENU_CODE=?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setInt(2, menu.getMenuCode());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }

        return result;
    }

    // JPA 스타일
    //public int save(){}
}
