package org.kodluyoruz.mybank.repository.daoimpl;

import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer addCustomer(Customer customer);

    List<Customer> listele ();

}
