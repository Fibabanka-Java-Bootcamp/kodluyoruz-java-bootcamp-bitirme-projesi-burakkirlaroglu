package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    List<Address> findAll();

}
