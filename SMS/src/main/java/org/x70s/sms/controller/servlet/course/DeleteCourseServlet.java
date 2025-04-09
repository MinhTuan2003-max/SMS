package org.x70s.sms.controller.servlet.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.service.CourseService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DeleteCourseServlet", value = "/course/delete")
public class DeleteCourseServlet extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        courseService.deleteById(id);

        String successMessage = "Course deleted successfully!";
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/course/find-all?message=" + URLEncoder.encode(successMessage, StandardCharsets.UTF_8));
    }
}
