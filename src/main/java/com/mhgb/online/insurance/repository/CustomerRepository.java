package com.mhgb.online.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mhgb.online.insurance.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query(value = "SELECT CUSTOMER_NAME,PASSWORD FROM CUSTOMER WHERE CUSTOMER_ID=?", nativeQuery = true)
	public String findCustomerPasswordById(long usedId);
}
