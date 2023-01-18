package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {


    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("Server UP");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        String ip = req.getRemoteAddr();
        String agent = req.getHeader("user-agent");
        System.out.println( agent );


        if ( url.equals("/loginError") ) {
            filterChain.doFilter(req, res);
        }else {
            String[] urls = {"/register", "/login"};
            boolean loginStatus = true;
            for (String urlSession : urls) {
                if (urlSession.equals(url)) {
                    loginStatus = false;
                }
            }

            if (loginStatus) {
                boolean status = req.getSession().getAttribute("user") == null;
                if (status) {
                    res.sendRedirect("/loginError");
                } else {
                    filterChain.doFilter(req, res);
                }
            } else {
                filterChain.doFilter(req, res);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("Server DOWN");
    }
}
