package org.x70s.sms.controller.servlet.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x70s.sms.entity.Course;
import org.x70s.sms.service.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCourseServlet", value = "/course/find-all")
public class GetCourseServlet extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 5;

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
            // Use default values if parsing fails
        }

        List<Course> courses = courseService.findAllPaginated(page, pageSize);
        int totalPages = courseService.getTotalPages(pageSize);

        req.setAttribute("courses", courses);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/page/course/course-list.jsp").forward(req, resp);
    }
}

