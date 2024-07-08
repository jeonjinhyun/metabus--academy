package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static com.ohgiraffers.common.Template.close;
import static com.ohgiraffers.common.Template.getConnection;

public class Application2 {
    public static void main(String[] args) {
        // employee 테이블에서 '중' 이 이름에 포함된 사원을 조회하세요(중 이라고 하는 글자를 스캐너로 입력)
        // 단, List<EmployeeDTO>에 담아서 출력하세요
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 사원의 한글자를 입력하셍요");
        String empName = sc.nextLine();
        empName="%"+empName+"%";

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO selectedEmp = null;

        try {
            String query = "SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empName);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                selectedEmp = new EmployeeDTO();
                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        System.out.println(selectedEmp);
    }
}
