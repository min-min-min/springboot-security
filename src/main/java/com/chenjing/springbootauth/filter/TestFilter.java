package com.chenjing.springbootauth.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Chenjing on 2017/9/8.
 */
@WebFilter(filterName = "testFilter", urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("test init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("test do");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("test end");
    }
}
