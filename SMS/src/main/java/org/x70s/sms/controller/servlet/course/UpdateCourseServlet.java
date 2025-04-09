package org.x70s.sms.controller.servlet.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.Course;
import org.x70s.sms.service.CourseService;

import java.io.IOException;

@WebServlet(name = "UpdateCourseServlet", urlPatterns = "/course/update")
public class UpdateCourseServlet extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Course course = courseService.findById(id);
        req.setAttribute("course", course);
        req.getRequestDispatcher("/page/course/update-course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("courseId"));
        String name = req.getParameter("courseName");
        String code = req.getParameter("courseCode");
        Integer credit = Integer.parseInt(req.getParameter("courseCredit"));
        Course course = courseService.findById(id);
        course.setName(name);
        course.setCode(code);
        course.setCredit(credit);
        courseService.update(course);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/course/find-all");
    }
}
