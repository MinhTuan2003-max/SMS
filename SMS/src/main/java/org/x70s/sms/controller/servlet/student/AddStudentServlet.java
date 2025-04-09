package org.x70s.sms.controller.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.Course;
import org.x70s.sms.entity.Student;
import org.x70s.sms.service.CourseService;
import org.x70s.sms.service.StudentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddStudentServlet", value = "/student/add-new")
public class AddStudentServlet extends HttpServlet {

    private CourseService courseService;
    private StudentService studentService;


    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.findAll();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/page/student/add-new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("studentName");
        Integer semester = Integer.parseInt(req.getParameter("semester"));
        String[] selectedCourseIds = req.getParameterValues("courseIds");
        Student student = new Student();
        student.setName(name);
        student.setSemester(semester);
        List<Course> courses = new ArrayList<>();
        if (selectedCourseIds != null) {
            for (String courseId : selectedCourseIds) {
                Long id = Long.parseLong(courseId);
                Course course = courseService.findById(id);
                if (course != null) {
                    courses.add(course);
                }
            }
        }
        student.setCourses(courses);
        studentService.save(student);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/student/find-all");
    }
}
