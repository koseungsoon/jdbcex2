package com.green.jdbcex.filter;


import com.green.jdbcex.dto.MemberDTO;
import com.green.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

//필터 적용시 @WebFilter추가 , 특정 경로를 지정해서 해당 경로의 요청에 대해서 doFilter()를 실행,
// todo라는 경로를 통해서는 중요한 자료가 있어서 로그인 여부를 체크하도록 할 수 있다.
@WebFilter(urlPatterns = {"/todo/*"})  // /todo/로 시작하는 모든 경로에 대해서 필터링 시도
@Log4j2
public class LoginCheckFilter implements Filter {

//필터링이 필요한 로직을 구현
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("Login check filter....");


        //ServletRequest의 하위인 HttpServletRequest의 객체에서 세션을 얻어와야함
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session =req.getSession();

        //세션에서 온 로그인정보가 없을경우에는 로그인 화면으로 가게 해야함



            //쿠키를 체크
            //이제는 로그인시에 기존에 remeber-me 쿠키를 발행했는지 여부를 체크해야함
            //remember-me 쿠키를 찾아야함

        log.info("세션에서 loginInfo에 대한 값"+session.getAttribute("loginInfo"));
        if(session.getAttribute("loginInfo") == null){
            Cookie cookie = findCookie(req.getCookies(), "remember-me");

            log.info("cookie 유무?"+cookie);


            if(cookie != null){

                log.info("쿠키가 존재합니다");
                String uuid  = cookie.getValue();

                try {
                    MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);

                    log.info("쿠키의 값으로 조회한 사용자 정보: " + memberDTO );


                    //회원정보를 세션에 추가
                    session.setAttribute("loginInfo", memberDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                filterChain.doFilter(request, response);
                return;
            }

            resp.sendRedirect("/jdbcex/login");
            return;
        }
        filterChain.doFilter(request, response);
        }




    private Cookie findCookie(Cookie[] cookies, String name){

        if(cookies == null || cookies.length == 0){
            return null;
        }

        Optional<Cookie> result = Arrays.stream(cookies).filter(ck -> ck.getName().equals(name)).findFirst();

        return result.isPresent()?result.get():null;
    }
}
