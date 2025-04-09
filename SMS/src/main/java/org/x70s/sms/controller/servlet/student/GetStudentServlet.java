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
import java.util.List;

@WebServlet(name = "GetStudentServlet", value = {"/student/find-all"})
public class GetStudentServlet extends HttpServlet {

    private StudentService studentService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseIDParam = req.getParameter("courseID");

        long courseId = 0;
        if (courseIDParam != null && !courseIDParam.isEmpty()) {
            try {
                courseId = Long.parseLong(courseIDParam);
            } catch (NumberFormatException e) {
                courseId = 0;
            }
        }

        int page = 1;
        int pageSize = 6;
        try {
            String pageParam = req.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
            }

            String pageSizeParam = req.getParameter("pageSize");
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
            }
        } catch (NumberFormatException e) {
            // Defaults will be used if parameters are missing or invalid
        }

        req.setAttribute("courses", courseService.findAll());
        if (courseId == 0) {
            req.setAttribute("students", studentService.findAllPaginated(page, pageSize));
        } else {
            req.setAttribute("students", studentService.findByCourseIdPaginated(courseId, page, pageSize));
        }

        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("totalPages", studentService.getTotalPages(pageSize, courseId));

        req.getRequestDispatcher("/page/student/student-list.jsp").forward(req, resp);
    }

}
