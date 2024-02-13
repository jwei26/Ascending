package org.example.repository;

import org.example.model.Department;
import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao{

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    @Override
    public User getUserByCredientials(String email, String password) {
        String hql = "FROM User as u WHERE lower(u.email) = :email AND u.password = :password";
        logger.info(String.format("User email: %s, password %s", email, password));
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.trim().toLowerCase());
            query.setParameter("password", password);
            User user = query.uniqueResult();
            return user;
        } catch (Exception exception) {
            logger.error("GetDepartments Error when Open session or Close session error", exception);
            throw exception;
        }
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            String hql = "from User where id = :userId";
            Query<User> query = session.createQuery(hql);
            query.setParameter("userId", id);
            user = query.uniqueResult();
            session.close();
            return user;
        } catch (HibernateException exception) {
            logger.error("GetUserById Error when Open session or Close session error", exception);
            throw exception;
        }
    }
}
