package com.ohgiraffers.section01.insert;

import com.ohgiraffers.common.Template;
import com.ohgiraffers.model.dto.MenuDTO;

import java.sql.Connection;

import static com.ohgiraffers.common.Template.*;

//Service
// 주 역할 : 트랜잭션 담당
// 하나의 트랜잭션은 하나의 Connection을 가진다.
public class MenuRegistService {
    public boolean registMenu(MenuDTO menu){
        Connection con = getConnection();

        MenuDAO menuDAO = new MenuDAO();
        int result = menuDAO.insertMenu(con, menu);

        if(result>0){
            commit(con);
        }else{
            rollback(con);
        }

        close(con);

        return result>0;
    }

    public boolean updateMenu(MenuDTO menu){
        Connection con = getConnection();

        MenuDAO menuDAO = new MenuDAO();
        int result = menuDAO.updateMenu(con, menu);

        if(result>0){
            commit(con);
        }else{
            rollback(con);
        }

        close(con);

        return result>0;
    }
}
