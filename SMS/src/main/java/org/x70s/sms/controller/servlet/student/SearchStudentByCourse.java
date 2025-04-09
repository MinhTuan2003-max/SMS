package org.x70s.sms.controller.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.service.CourseService;
import org.x70s.sms.service.StudentService;

import java.io.IOException;

@WebServlet(name = "SearchStudentByCourse", value = "/student/search-student-by-course")
public class SearchStudentByCourse extends HttpServlet {

    private StudentService studentService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID = req.getParameter("courseID");
        courseID = (courseID == null) ? "0" : courseID;
        long id = Long.parseLong(courseID);
        req.setAttribute("courses", courseService.findAll());
        if (id == 0) {
            req.setAttribute("students", studentService.findAll());
        } else {
            req.setAttribute("students", studentService.findByCourseId(id));
        }
        req.getRequestDispatcher("/page/student/search-student-by-course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID = req.getParameter("courseID");
        courseID = courseID == null ? "0" : courseID;
        long id = Long.parseLong(courseID);
        req.setAttribute("courses", courseService.findAll());
        if (id == 0) {
            req.setAttribute("students", studentService.findAll());
        } else {
            req.setAttribute("students", studentService.findByCourseId(id));
        }
        req.getRequestDispatcher("/page/student/search-student-by-course.jsp").forward(req, resp);
    }
}
