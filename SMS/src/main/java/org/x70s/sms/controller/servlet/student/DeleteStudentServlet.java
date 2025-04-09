package org.x70s.sms.controller.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.Student;
import org.x70s.sms.service.StudentService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DeleteStudentServlet", value = "/student/delete")
public class DeleteStudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        studentService.deleteById(id);

        String successMessage = "Student deleted successfully!";
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/student/find-all?message=" + URLEncoder.encode(successMessage, StandardCharsets.UTF_8));
    }
}
