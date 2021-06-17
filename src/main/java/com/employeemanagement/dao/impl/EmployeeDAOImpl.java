package com.employeemanagement.dao.impl;

import com.employeemanagement.dao.EmployeeDAO;
import com.employeemanagement.entity.EmployeeDetails;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean isUserExist(String userName) {
        Session currentSession = entityManager.unwrap(Session.class);
        String hql = "from EmployeeDetails emp e where e.userName =: userName";
        Query query = currentSession.createQuery(hql, EmployeeDetails.class);
        query.setParameter("userName", userName);
        List list = query.getResultList();
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void saveEmployee(EmployeeDetails employeeDetails) throws Exception {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.save(employeeDetails);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    @Transactional
    public EmployeeDetails getEmployeeByUserName(String userName) {
        Session currentSession = entityManager.unwrap(Session.class);
        String hql = "from EmployeeDetails emp e where e.userName =: userName";
        Query query = currentSession.createQuery(hql, EmployeeDetails.class);
        query.setParameter("userName", userName);
        return (EmployeeDetails) query.getSingleResult();
    }

    @Override
    @Transactional
    public List getEmployeeList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from EmployeeDetails", EmployeeDetails.class);
        return query.getResultList();
    }
}
