package org.example.repository;

import org.example.model.Department;
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
public class DepartmentHibernateDao implements IDepartmentDAO{
    //private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    @Override
    public List<Department> getDepartments() {
        List<Department> departments;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            String hql = "from Department";
            Query<Department> query = session.createQuery(hql);
            departments = query.list();
            session.close();
            return departments;
        } catch (HibernateException exception) {
            logger.error("GetDepartments Error when Open session or Close session error", exception);
            throw exception;
        }

    }

    @Override
    public Department getById(long id) {
        Department department = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            String hql = "from Department where id = :departmentId";
            Query<Department> query = session.createQuery(hql);
            query.setParameter("departmentId", id);
            department = query.uniqueResult();
            session.close();
            return department;
        } catch (HibernateException exception) {
            logger.error("GetDepartmentById Error when Open session or Close session error", exception);
            throw exception;
        }
    }




    @Override
    public void save(Department department) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            session.close();
        } catch (HibernateException exception) {
            if(transaction != null) {
                logger.error("Transaction save failed, Rollback");
                transaction.rollback();
            }
            logger.error("Error to save ${}", department, exception);
            throw exception;
        }
    }

    @Override
    public void updateName(long id, String name) {

    }

    @Override
    public void deleteDepartments(long id) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "delete Department where id = :ID";
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
