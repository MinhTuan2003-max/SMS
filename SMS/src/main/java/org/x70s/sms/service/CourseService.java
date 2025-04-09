package org.x70s.sms.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.x70s.sms.entity.Course;
import org.x70s.sms.entity.Student;
import org.x70s.sms.repository.CourseDAO;
import org.x70s.sms.utils.SmsHibernateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public Course findById(Long id) {
        return courseDAO.findById(id).orElseThrow();
    }

    public Course save(Course course) {
        return courseDAO.saveOrUpdate(course);
    }

    public Course update(Course course) {
        return courseDAO.update(course);
    }

    public void delete(Course course) {
        courseDAO.delete(course);
    }

    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    public void deleteById(Long id) {
        courseDAO.deleteById(id);
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://TUAN-NE:1433;databaseName=sms;trustServerCertificate=true";
        String user = "sa";
        String password = "minhtuanha2003";
        return DriverManager.getConnection(url, user, password);
    }

    public List<Student> listStudentsByCourseId(Long courseId) {
        String sql = "SELECT s.id, s.name, s.semester " +
                "FROM student s " +
                "INNER JOIN student_course sc ON s.id = sc.student_id " +
                "INNER JOIN course c ON c.id = sc.course_id " +
                "WHERE c.id = ? " +
                "ORDER BY s.id";

        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSemester(Integer.valueOf(rs.getString("semester")));
                    students.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Course> findAllPaginated(int page, int pageSize) {
        return courseDAO.findAllPaginated(page, pageSize);
    }

    public int getTotalPages(int pageSize) {
        try (Session session = SmsHibernateUtils.getSessionFactory().openSession()) {
            Long count = (Long) session.createQuery("SELECT COUNT(*) FROM Course").uniqueResult();
            return (int) Math.ceil((double) count / pageSize);
        }
    }

    public Course findByCodeOrName(String code) {
        try (Session session = SmsHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM Course WHERE code = :code";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("code", code);
            Course course = query.uniqueResult();
            session.getTransaction().commit();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
