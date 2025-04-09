package org.x70s.sms.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.x70s.sms.entity.Student;
import org.x70s.sms.repository.StudentDAO;
import org.x70s.sms.utils.SmsHibernateUtils;

import java.util.Collections;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    public Student findById(Long id) {
        return studentDAO.findById(id).orElseThrow();
        // TODO Implement errorHandler logic
    }

    public List<Student> findByCourseId(Long courseId) {
        return studentDAO.findByCourseId(courseId);
    }

    public Student save(Student student) {
        return studentDAO.saveOrUpdate(student);
    }

    public Student update(Student student) {
        return studentDAO.update(student);
    }

    public void deleteById(Long id) {
        studentDAO.deleteById(id);
    }

    public void delete(Student student) {
        studentDAO.delete(student);
    }

    public List<Student> findAllPaginated(int page, int pageSize) {
        return studentDAO.findAllPaginated(page, pageSize);
    }

    public List<Student> findByCourseIdPaginated(long courseId, int page, int pageSize) {
        try (Session session = SmsHibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("courseId", courseId);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int getTotalPages(int pageSize, long courseId) {
        try (Session session = SmsHibernateUtils.getSessionFactory().openSession()) {
            Long totalRecords;
            if (courseId == 0) {
                Query<Long> countQuery = session.createQuery("SELECT COUNT(s.id) FROM Student s", Long.class);
                totalRecords = countQuery.uniqueResult();
            } else {
                String hql = "SELECT COUNT(s.id) FROM Student s JOIN s.courses c WHERE c.id = :courseId";
                Query<Long> countQuery = session.createQuery(hql, Long.class);
                countQuery.setParameter("courseId", courseId);
                totalRecords = countQuery.uniqueResult();
            }
            return (int) Math.ceil((double) totalRecords / pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

}
