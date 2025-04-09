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

@WebServlet(name = "UpdateStudentServlet", value = "/student/update")
public class UpdateStudentServlet extends HttpServlet {

    private StudentService studentService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.parseLong(req.getParameter("id"));
        Student student = studentService.findById(studentId);
        List<Course> allCourses = courseService.findAll();

        req.setAttribute("student", student);
        req.setAttribute("courses", allCourses);

        req.setAttribute("selectedCourses", student.getCourses());

        req.getRequestDispatcher("/page/student/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.parseLong(req.getParameter("id"));
        String studentName = req.getParameter("name");
        Integer semester = Integer.parseInt(req.getParameter("semester"));
        String[] selectedCourseIds = req.getParameterValues("courseIds");

        Student student = studentService.findById(studentId);
        student.setName(studentName);
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
        studentService.update(student);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/student/find-all");
    }
}
