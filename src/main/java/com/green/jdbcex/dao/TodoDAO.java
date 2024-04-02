package com.green.jdbcex.dao;

import com.green.jdbcex.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TodoDAO {

    public String getTime() {

        String now = null;

        //자원을 가지고 하는 예외처리, try with Resource처리

//        ConnectionUtil.INSTANCE.getConnection();
        //이전방식
//        try {
//            ConnectionUtil.INSTANCE.getConnection();
//        } catch (Exception e) {
//            System.out.println("커넥션 관련 예외 발생");
//        }

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("select now();");
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            now = rs.getString(1);

        } catch (Exception e) {
            System.out.println("커넥션 관련 예외 발생");
        }


        return now;
    }

    //교재 코드
    public void insert(TodoVO todoVO) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());

        preparedStatement.executeUpdate();

    }

}
