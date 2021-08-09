package com.southwind.filter;

import com.southwind.entity.Admin;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
//urlPatterns:表示要拦截的请求
@WebFilter(urlPatterns = {"/index.html","/menu/redirect/index"},filterName = "adminFilter")
public class AdminFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 登录拦截器
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if(admin == null){  //未登录
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("login.html");
        }else{  //放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}