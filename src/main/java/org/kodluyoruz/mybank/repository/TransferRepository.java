package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Transfer;
import org.springframework.data.repository.CrudRepository;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {
}
