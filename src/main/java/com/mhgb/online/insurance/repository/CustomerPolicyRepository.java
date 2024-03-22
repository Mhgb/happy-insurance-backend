package com.mhgb.online.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mhgb.online.insurance.model.CustomerPolicy;
import com.mhgb.online.insurance.model.CustomerPolicyDetails;

@Repository
public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Long> {

	@Query("SELECT new com.mhgb.online.insurance.model.CustomerPolicyDetails(cp.id as id,cp.customerId as customerId,cus.customerName as customerName,pol.policyId as policyId,pol.policyType as policyType,pol.policyTitle as policyTitle,pol.sumAssured as sumAssured,pol.premium as premium,pol.term as term,cp.commencementDate as commencementDate,cp.status as status,cp.maturityDate as maturityDate,cp.nextDueDate as nextDueDate,cp.insurer as insurer) FROM CustomerPolicy cp JOIN Customer cus ON cp.customerId = cus.customerId AND cp.customerId = ?1 JOIN Policy pol ON cp.policyId = pol.policyId")
	public Page<CustomerPolicyDetails> findAllPoliciesForCustomerId(Long cust_id, PageRequest pageRequest);
}
