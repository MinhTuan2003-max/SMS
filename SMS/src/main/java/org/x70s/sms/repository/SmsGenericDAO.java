package org.x70s.sms.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.x70s.sms.utils.SmsHibernateUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SmsGenericDAO<T, ID> {
    SessionFactory sessionFactory = SmsHibernateUtils.getSessionFactory();
    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public SmsGenericDAO() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType parameterizedType) {
            this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        } else {
            throw new IllegalArgumentException("Could not determine entity class for " + getClass());
        }
    }

    public List<T> findAll() {
        List<T> list;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String jql = "SELECT s FROM " + getEntityClass().getName() + " s ";
            Query<T> query = session.createQuery(jql, getEntityClass());
            list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public Optional<T> findById(ID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T data = session.get(getEntityClass(), id);
            session.getTransaction().commit();
            return Optional.ofNullable(data);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public T saveOrUpdate(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    public T update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return entity;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }

    public void deleteById(ID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(getEntityClass(), id));
            session.getTransaction().commit();
        }
    }

    public List<T> saveAll(List<T> entities) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (T entity : entities) {
                session.persist(entity);
            }
            session.getTransaction().commit();
            return entities;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType parameterizedType) {
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        } else if (type instanceof Class) {
            Type superType = ((Class<?>) type).getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                return (Class<T>) ((ParameterizedType) superType).getActualTypeArguments()[0];
            }
        }
        throw new IllegalArgumentException("Could not determine entity class for " + getClass());
    }

    @SuppressWarnings("unchecked")
    private Class<T> getIdClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType parameterizedType) {
            return (Class<T>) parameterizedType.getActualTypeArguments()[1];
        } else if (type instanceof Class) {
            Type superType = ((Class<?>) type).getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                return (Class<T>) ((ParameterizedType) superType).getActualTypeArguments()[1];
            }
        }
        throw new IllegalArgumentException("Could not determine entity class for " + getClass());
    }

    public List<T> findAllPaginated(int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT s FROM " + getEntityClass().getName() + " s ORDER BY s.id DESC";
            Query<T> query = session.createQuery(hql, entityClass);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
