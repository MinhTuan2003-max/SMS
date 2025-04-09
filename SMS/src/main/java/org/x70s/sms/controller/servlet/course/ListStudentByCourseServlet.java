package org.x70s.sms.controller.servlet.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.Student;
import org.x70s.sms.service.CourseService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListStudentByCourseServlet", value = "/course/list-student-by-course")
public class ListStudentByCourseServlet extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        List<Student> students = courseService.listStudentsByCourseId(id);
        req.setAttribute("students", students);
        req.getRequestDispatcher("/page/course/list-student-by-course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        List<Student> students = courseService.listStudentsByCourseId(id);
        req.setAttribute("students", students);
        req.getRequestDispatcher("/page/course/list-student-by-course.jsp").forward(req, resp);
    }
}
