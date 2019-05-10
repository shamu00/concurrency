package com.shamu.concurrency;

import com.shamu.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化的时候
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 核心操作
        HttpServletRequest httprequest = (HttpServletRequest) request;
        //httprequest.getSession().getAttribute("user");
        log.info("do filter, {}, {}", Thread.currentThread().getId(),  httprequest.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 不再使用的时候
    }
}
