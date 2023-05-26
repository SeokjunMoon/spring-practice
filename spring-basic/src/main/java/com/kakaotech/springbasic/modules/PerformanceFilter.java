package com.kakaotech.springbasic.modules;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class PerformanceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start_time = System.currentTimeMillis();

        chain.doFilter(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        String referrer = req.getHeader("referrer");
        String method = req.getMethod();
        System.out.print("[ " + referrer + " ] -> " + method + "[ " + req.getRequestURI() + " ]");
        System.out.println(" 소요시간 = " + (System.currentTimeMillis() - start_time) + "ms");
    }

    @Override
    public void destroy() {

    }
}
