package com.example.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterConfig implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;


        String url = req.getRequestURI();
        String[] urls = {"/", "/login"};
        boolean loginStatus = true;
        for (String item : urls) {
            if (url.equals(item)) {
                loginStatus = false;
            }
        }
        if (loginStatus) {
            boolean status = req.getSession().getAttribute("admin") == null;
            if (status) {
                res.sendRedirect("http://localhost:8090/");
            }
        }
        filterChain.doFilter(req, res);
    }
}
