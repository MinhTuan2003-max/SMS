package org.x70s.sms.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.x70s.sms.entity.Student;

import java.util.List;

public class StudentDAO extends SmsGenericDAO<Student, Long> {

        public List<Student> findByCourseId(Long courseId) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                String hql = "SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId";
                Query<Student> query = session.createQuery(hql, Student.class);
                query.setParameter("courseId", courseId);
                List<Student> students = query.list();
                session.getTransaction().commit();
                return students;
            } catch (HibernateException e) {
                throw new HibernateException("Error finding students by course ID", e);
            }
        }
}
