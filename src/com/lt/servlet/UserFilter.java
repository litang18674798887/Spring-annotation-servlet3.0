package com.lt.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

public class UserFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //过滤请求
        System.out.println("UserFilter....doFilter");
        //放行
        chain.doFilter(request,response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void destroy() {

    }
}
