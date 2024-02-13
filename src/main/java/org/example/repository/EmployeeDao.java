package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao implements IEmployeeDao{
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    @Override
    public List<Employee> getEmployee() {
        List<Employee> employees;
        Session session = sessionFactory.openSession();
        try {
            String hql = "from Employee ";
            Query<Employee> query = session.createQuery(hql);
            employees = query.list();
            session.close();
            return employees;
        } catch (HibernateException exception) {
            logger.error("Get Employee Error when Open session or Close session error", exception);
            throw exception;
        }

    }

    @Override
    public void save(Employee employee) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            session.close();
        } catch (HibernateException exception) {
            if(transaction != null) {
                logger.error("Transaction save failed, Rollback");
                transaction.rollback();
            }
            logger.error("Error to save ${}", employee, exception);
            throw exception;
        }
    }
    @Override
    public void delete(long id) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "delete Employee where id = :ID";
            Query query = session.createQuery(hql);
            query.setParameter("ID", id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException exception) {
            if(transaction != null) {
                logger.error("Transaction delete failed, Rollback");
                transaction.rollback();
            }
            logger.error("Error to delete Department ${}", id, exception);
            throw exception;
        }
    }
}
