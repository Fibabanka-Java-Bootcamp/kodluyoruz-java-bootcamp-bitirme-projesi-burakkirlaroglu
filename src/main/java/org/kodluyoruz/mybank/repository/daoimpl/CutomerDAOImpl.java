package org.kodluyoruz.mybank.repository.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CutomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private final EntityManager entityManager;
    Session currenSession;

    @Autowired
    public CutomerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    @Override
//    public void addCustomer(Customer customer) {
//        currenSession = entityManager.unwrap(Session.class);
//        currenSession.saveOrUpdate(customer);
//    }


    @Override
    public Customer addCustomer(Customer customer) {
        currenSession = entityManager.unwrap(Session.class);
        currenSession.save(customer);
        return customer;
    }

    @Override
    public List<Customer> listele() {

        currenSession = entityManager.unwrap(Session.class);

        Query<Customer> theQuery = currenSession.createQuery("select a from Customer a", Customer.class);
        List<Customer> customers = theQuery.getResultList();
        return customers;

    }
}
