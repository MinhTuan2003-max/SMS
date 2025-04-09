package org.x70s.sms.controller.servlet.home;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.AppUser;

import java.io.IOException;

@WebServlet(name = "HomeServlet", value = {"/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppUser user = (AppUser) req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/page/home/home.jsp").forward(req, resp);
    }
}
