package com.mhgb.online.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhgb.online.insurance.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
