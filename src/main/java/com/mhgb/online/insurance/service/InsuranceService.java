package com.mhgb.online.insurance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.mhgb.online.insurance.model.Customer;
import com.mhgb.online.insurance.model.CustomerPolicy;
import com.mhgb.online.insurance.model.CustomerPolicyDetails;
import com.mhgb.online.insurance.model.Feedback;
import com.mhgb.online.insurance.model.Policy;
import com.mhgb.online.insurance.repository.CustomerPolicyRepository;
import com.mhgb.online.insurance.repository.CustomerRepository;
import com.mhgb.online.insurance.repository.FeedbackRepository;
import com.mhgb.online.insurance.repository.PolicyRepository;

@Service
public class InsuranceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceService.class);

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PolicyRepository policyRepo;

	@Autowired
	private CustomerPolicyRepository custPolicyRepo;

	@Autowired
	private FeedbackRepository feedbackRepo;

	public Customer registerCustomer(Customer customer) {

		Customer registerdCustomer = customerRepo.save(customer);

		return registerdCustomer;
	}

	public Map<String, String> validateUser(String userId, String password) {

		boolean validCustomer = false;
		LOGGER.info("Authenticating User Id: " + userId);

		Map<String, String> map = new HashMap<>();
		String fetchedResult = customerRepo.findCustomerPasswordById(Long.valueOf(userId));

		LOGGER.info(fetchedResult);
		if (null != fetchedResult && !fetchedResult.isBlank()) {

			String[] result = fetchedResult.split(",");
			String userName = result[0];
			String retrievedPassword = result[1];

			if (retrievedPassword.equalsIgnoreCase(password)) {
				map.put("userId", userId);
				map.put("username", userName);
				validCustomer = true;
			}
			map.put("validCustomer", String.valueOf(validCustomer));
		}

		LOGGER.info("Valid user: " + validCustomer);

		return map;
	}

	public Map<String, List<Policy>> getAllPolicies() {

		List<Policy> policyList = policyRepo.findAll();

		Map<String, List<Policy>> policyMap = formPolicyMap(policyList);

		System.out.println(policyMap);

		return policyMap;
	}

	private Map<String, List<Policy>> formPolicyMap(List<Policy> policyList) {

		Map<String, List<Policy>> policyMap = new HashMap<>();

		for (Policy policy : policyList) {
			String policy_type = policy.getPolicyType();

			if (null != policyMap.get(policy_type)) {
				policyMap.get(policy_type).add(policy);
			} else {
				ArrayList<Policy> list = new ArrayList<Policy>();
				list.add(policy);
				policyMap.put(policy_type, list);
			}
		}

		return policyMap;
	}

	public CustomerPolicy saveCustomerPolicy(CustomerPolicy customerPolicy) {

		CustomerPolicy savedCustomerPolicy = custPolicyRepo.save(customerPolicy);
		return savedCustomerPolicy;
	}

	public Policy findPolicyById(Long policyId) {

		Optional<Policy> policyOp = policyRepo.findById(policyId);

		return policyOp.get();
	}

	public Page<CustomerPolicyDetails> fetchAllYourPolicies(Long cust_id, Integer pageNo, Integer pageSize) {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		Page<CustomerPolicyDetails> policyListForCustomerId = custPolicyRepo.findAllPoliciesForCustomerId(cust_id,
				pageRequest);

		for (CustomerPolicyDetails details : policyListForCustomerId) {
			LOGGER.info(details.getPolicyTitle());
		}

		return policyListForCustomerId;
	}

	public Customer getCustomerDetails(long id) {

		Optional<Customer> custOp = customerRepo.findById(id);

		return custOp.get();
//		if(custOp.isPresent()) {
//			new ResponseEntity<Customer>(custOp.get(), HttpStatus.OK);
//		}
//		return new ResponseEntity<Customer>(new Customer(), HttpStatus.NOT_FOUND);
	}

	public Feedback saveFeedback(Feedback feedback) {

		Feedback savedFeedback = feedbackRepo.save(feedback);
		return savedFeedback;
	}

	public List<Feedback> getFeedbacks() {
		PageRequest pageRequest = PageRequest.of(0, 5);

		Page<Feedback> recentFeedbacks = feedbackRepo.findAll(pageRequest);
		List<Feedback> recentFeedbacksList = recentFeedbacks.getContent();
		
		return recentFeedbacksList;
	}

}
