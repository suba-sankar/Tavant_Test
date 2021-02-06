package com.tavant.practicalrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tavant.practicalrestapi.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
