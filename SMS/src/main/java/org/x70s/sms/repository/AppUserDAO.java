package org.x70s.sms.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.x70s.sms.entity.AppUser;

import java.util.Optional;

public class AppUserDAO extends SmsGenericDAO<AppUser, Long> {
    public Optional<AppUser> findByUserAndPassword(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT au ");
            sql.append(" FROM AppUser au ");
            sql.append(" WHERE au.username = :username ");
            sql.append(" AND au.password = :password ");
            Query<AppUser> query = session.createQuery(sql.toString(), AppUser.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
