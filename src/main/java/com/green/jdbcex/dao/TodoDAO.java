package com.green.jdbcex.dao;

import com.green.jdbcex.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    //전체 글 목록 보기
    public List<TodoVO> selectAll() throws Exception{

        List<TodoVO> todoVOList = new ArrayList<>();

        String sql = "select * from tbl_todo" ;

        @Cleanup // try catch 리소스문을 대체 (가독성 향상)
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup
        PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate( rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

            todoVOList.add(vo);
        }

        return todoVOList;


    }

    //글 하나 보기
    public TodoVO selectOne(Long tno)throws Exception {

        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate( resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

    // 글 삭제 하기
    public void deleteOne(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection    connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        preparedStatement.executeUpdate();
    }

    //글 수정하기
    public void updateOne(TodoVO todoVO)throws Exception{

        String sql = "update tbl_todo set title =?, dueDate = ?, finished = ? where tno =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());

        preparedStatement.executeUpdate();
    }


}
