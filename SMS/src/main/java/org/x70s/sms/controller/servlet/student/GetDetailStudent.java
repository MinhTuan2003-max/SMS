package org.x70s.sms.controller.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.service.StudentService;

import java.io.IOException;

@WebServlet(name = "GetDetailStudent", value = "/student/get-detail")
public class GetDetailStudent extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("student", studentService.findById(id));
        req.getRequestDispatcher("/page/student/student-detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("student", studentService.findById(id));
        req.getRequestDispatcher("/page/student/student-detail.jsp").forward(req, resp);
    }
}
