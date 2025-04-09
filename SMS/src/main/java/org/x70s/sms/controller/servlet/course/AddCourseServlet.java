package org.x70s.sms.controller.servlet.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.Course;
import org.x70s.sms.service.CourseService;

import java.io.IOException;

@WebServlet(name = "AddCourseServlet", value = "/course/add-new")
public class AddCourseServlet extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/course/add-course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("courseName");
        String code = req.getParameter("courseCode");
        Integer credit = Integer.parseInt(req.getParameter("courseCredit"));

        Course existingCourse = courseService.findByCodeOrName(code);
        if (existingCourse != null) {
            String errorMessage = "A course with this code already exists.";
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("courseName", name);
            req.setAttribute("courseCode", code);
            req.setAttribute("courseCredit", credit);

            req.getRequestDispatcher("/page/course/add-course.jsp").forward(req, resp);
            return;
        }

        Course course = new Course();
        course.setName(name);
        course.setCode(code);
        course.setCredit(credit);
        courseService.save(course);

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/course/find-all");
    }

}
