package com.green.jdbcex.controller;

import com.green.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/todo/remove")
@Log4j2
public class TodoRemoveController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        try {
            Long tno =Long.parseLong(req.getParameter("tno"));
            log.info("삭제할 글번호: "+tno);
            todoService.delete(tno);
        } catch (Exception e) {
            log.info("글 삭제시 예외발생");
        }

        resp.sendRedirect("/jdbcex/todo/list");
    }
}
