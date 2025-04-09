package org.x70s.sms.controller.filter;

import org.x70s.sms.entity.AppUser;
import org.x70s.sms.entity.Authority;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"*"})
public class AuthorizationFilter implements Filter {

    private static final String[] PERMIT_All = {"/auth/login", "/auth/logout", "/home", "/assets", "/public", "/css", "/js", "/images"};
    private static final String[] ROLE_STUDENT = {"/student/search-student-by-course"};
    private static final String[] ROLE_ADMIN = {"/student/add-new", "/student/delete", "/course/add-new", "/course/update", "/course/delete", "/course/list-student-by-course"};
    private static final String[] ROLE_USER = {"/student/find-all", "/student/get-detail", "/course/find-all"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getServletPath();

        if (Arrays.stream(PERMIT_All).anyMatch(uri::startsWith) || Arrays.stream(ROLE_USER).anyMatch(uri::startsWith)) {
            chain.doFilter(request, response);
            return;
        }

        AppUser user = (AppUser) req.getSession().getAttribute("user");
        if (user != null) {
            List<Authority> authorities = user.getAuthorities();
            boolean hasAccess = false;

            if (Arrays.stream(ROLE_ADMIN).anyMatch(uri::startsWith)) {
                hasAccess = authorities.stream()
                        .anyMatch(auth -> auth.getCode().equalsIgnoreCase("ROLE_ADMIN"));
            }

            if (Arrays.stream(ROLE_STUDENT).anyMatch(uri::startsWith)) {
                hasAccess = authorities.stream()
                        .anyMatch(auth -> auth.getCode().equalsIgnoreCase("ROLE_STUDENT"));
            }

            if (Arrays.stream(new String[]{"/student/update"}).anyMatch(uri::startsWith)) {
                hasAccess = authorities.stream()
                        .anyMatch(auth -> auth.getCode().equalsIgnoreCase("ROLE_ADMIN") || auth.getCode().equalsIgnoreCase("ROLE_STUDENT"));
            }

            if (hasAccess) {
                chain.doFilter(request, response);
            } else {
                req.getRequestDispatcher("/page/error/401.jsp").forward(req, res);
            }
        } else {
            res.sendRedirect(req.getContextPath() + "/auth/login");
            return;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
