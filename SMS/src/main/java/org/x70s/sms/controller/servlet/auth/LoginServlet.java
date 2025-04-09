package org.x70s.sms.controller.servlet.auth;

import org.x70s.sms.entity.AppUser;
import org.x70s.sms.service.AppUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/auth/login")
public class LoginServlet extends HttpServlet {

    private AppUserService appUserService;

    @Override
    public void init() throws ServletException {
        appUserService = new AppUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/authentication/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AppUser user = appUserService.loadUser(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("../home");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/page/authentication/login.jsp").forward(req, resp);
        }
    }
}
