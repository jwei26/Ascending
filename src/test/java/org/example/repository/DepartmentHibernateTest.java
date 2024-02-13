package org.example.repository;

import org.example.model.Department;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.MockitoJUnitRunner;
import org.w3c.dom.ls.LSException;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentHibernateTest {
    @Mock
    private SessionFactory mockSessionFactory;
    @Mock
    private Session mockSession;
    @Mock
    private Query mockQuery;

    @Before
    public void setUp() {
        initMocks(this);
    }
    @Test
    public void getDepartment() {
        IDepartmentDAO departmentDAO = new DepartmentHibernateDao();
        Department department = new Department(1, "jane", "Ramdom", "US");
        try(MockedStatic mockedStatic = mockStatic(HibernateUtil.class)) {
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);

            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(List.of(department));
            doNothing().when(mockSession).close();

            List<Department> actualDepartment = departmentDAO.getDepartments();
            assertEquals(List.of(department), actualDepartment);
        }
    }
    
    @Test
    public void getDepartmentTest_getHibernateException_throwHibernateException() {
        IDepartmentDAO departmentDAO = new DepartmentHibernateDao();
        Department department = new Department(1, "jane", "Ramdom", "US");
        try(MockedStatic mockedStatic = mockStatic(HibernateUtil.class)) {
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);

            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(List.of(department));
            doThrow(HibernateException.class).when(mockSession).close();

            assertThrows(HibernateException.class, () -> departmentDAO.getDepartments());
        }
    }
}
