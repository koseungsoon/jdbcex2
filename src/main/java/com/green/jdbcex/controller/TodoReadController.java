package com.green.jdbcex.controller;

import com.green.jdbcex.dto.TodoDTO;
import com.green.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            Long tno = Long.parseLong(req.getParameter("tno"));

            log.info("tno: " + tno);
            TodoDTO todoDTO = todoService.get(tno);
            //모델 담기
            req.setAttribute("dto", todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);

            log.info("요청시 가져온 쿠키들: " + req.getCookies().toString());
            Cookie cookies[] = req.getCookies();
            for (Cookie cookie : cookies) {
                log.info("쿠키: " + cookie.getName());
            }

            //최근 본 글을 저장하기위한 사용자 정의 쿠키 구현 후 찾음
            // 'viewDotods'라는 이름의 쿠키를 찾고, 쿠키의 내용물을 검사한 후에 만일 조회한 적이 없는 번호라면
            // 쿠키의 내용물을 갱신해서 브라우저로 보내주는것, 쿠키를 번경할때는 다시 경로나 유효시간을 세팅해야함

            //쿠키 찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");

            log.info("찾은 쿠키의 이름: " + viewTodoCookie.getName());
            log.info("찾은 쿠기의 값: " + viewTodoCookie.getValue());

            String todoListStr = viewTodoCookie.getValue();

            boolean exist = false;

            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
                exist = true;
            }

            log.info("exist: " + exist);

            if (!exist) {
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60 * 60 * 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

        } catch (Exception e) {
            log.info("ReadController 예외발생");
        }
    }

    //쿠키가 여러개이므로 찾아야함
    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        Cookie targetCookie = null;

        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }

        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60 * 60 * 24);
        }

        return targetCookie;
    }

}


