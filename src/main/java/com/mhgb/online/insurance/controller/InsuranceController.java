package com.mhgb.online.insurance.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mhgb.online.insurance.model.Customer;
import com.mhgb.online.insurance.model.CustomerPolicy;
import com.mhgb.online.insurance.model.CustomerPolicyDetails;
import com.mhgb.online.insurance.model.Feedback;
import com.mhgb.online.insurance.model.Policy;
import com.mhgb.online.insurance.service.InsuranceService;

@CrossOrigin
@RestController
public class InsuranceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceController.class);

	@Autowired
	private InsuranceService service;

	@GetMapping
	public String welcome() {
		return "HelloWorld";
	}

	@PostMapping("/RegisterCustomer")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Map<String, String> requestData) {

		String name = requestData.get("name").trim();
		String email = requestData.get("email").trim();
		String password = requestData.get("password").trim();
		String address = requestData.get("address").trim();
		String nominee = requestData.get("nominee").trim();
		String relationship = requestData.get("relationship").trim();
		String contact = requestData.get("contact").trim();

		Customer customer = new Customer(name, email, password, address, contact, nominee, relationship);

		Customer registerCustomer = service.registerCustomer(customer);

		return new ResponseEntity<Customer>(registerCustomer, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> requestData) {
		String userId = requestData.get("userId").trim();
		String password = requestData.get("password").trim();

		Map<String, String> map = service.validateUser(userId, password);

		if (map != null && Boolean.parseBoolean(map.get("validCustomer"))) {
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}

		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/policies")
	public ResponseEntity<Map<String, List<Policy>>> getAllAvailablePolicies() {

		Map<String, List<Policy>> policyMap = service.getAllPolicies();

		if (policyMap != null) {
			return new ResponseEntity<Map<String, List<Policy>>>(policyMap, HttpStatus.OK);
		}

		return new ResponseEntity<Map<String, List<Policy>>>(new HashMap<String, List<Policy>>(), HttpStatus.OK);
	}

	@PostMapping("/confirmPolicy")
	public ResponseEntity<Object> confirmPolicy(@RequestBody Map<String, String> requestData) {

		Long userId = Long.valueOf(requestData.get("userId"));
		Long policyId = Long.valueOf(requestData.get("policyId"));
		String insurer = requestData.get("insurer");

		LOGGER.info("User ID: " + userId + " , Policy ID: " + policyId);
		Policy policy = service.findPolicyById(policyId);

		Date currentDate = new Date(System.currentTimeMillis());

		// Adding 1 year for nextDueDate
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.DATE, -1);

		Date nextDueDate = new Date(calendar.getTimeInMillis());

		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.YEAR, policy.getTerm());

		Date maturityDate = new Date(calendar1.getTimeInMillis());

		CustomerPolicy customerPolicy = new CustomerPolicy(userId, policy.getPolicyId(), "Active", currentDate,
				maturityDate, nextDueDate, insurer);
		CustomerPolicy savedCustomerPolicy = service.saveCustomerPolicy(customerPolicy);

		if (savedCustomerPolicy != null) {
			LOGGER.info("Policy id: " + savedCustomerPolicy.getPolicyId() + " is registered for the customer : "
					+ savedCustomerPolicy.getCustomerId() + " successfully");
			return ResponseEntity.status(HttpStatus.OK).body(savedCustomerPolicy);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savedCustomerPolicy);
	}

	@GetMapping("/view-your-policies")
	public ResponseEntity<Page<CustomerPolicyDetails>> getYourPolicies(@RequestParam Long cust_id,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "3") Integer pageSize) {
		Page<CustomerPolicyDetails> customerPolicies = service.fetchAllYourPolicies(cust_id, pageNo, pageSize);

		return new ResponseEntity<Page<CustomerPolicyDetails>>(customerPolicies, HttpStatus.OK);
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerDetails(@PathVariable long id) {
		return service.getCustomerDetails(id);
	}

	@GetMapping("/customer-nominee/{id}")
	public Customer getNomineeDetails(@PathVariable long id) {
		return service.getCustomerDetails(id);
	}

	@PatchMapping("/update-nominee-details")
	public ResponseEntity<Customer> updateNomineeDetails(@RequestBody Map<String, String> requestData) {

		String userId = requestData.get("userId");
		String nominee = requestData.get("nominee");
		String relationship = requestData.get("relationship");

		Customer customer = service.getCustomerDetails(Long.valueOf(userId));

		customer.setNomineeName(nominee);
		customer.setRelationship(relationship);

		Customer savedCustomer = service.registerCustomer(customer);
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.OK);
	}

	@PostMapping("/submit-feedback")
	public ResponseEntity<Feedback> saveFeedback(@RequestBody Map<String, String> requestData) {
		Long userId = Long.valueOf(requestData.get("userId"));
		String content = requestData.get("feedContent");
		String title = requestData.get("feedTitle");
		
		Feedback result = service.saveFeedback(new Feedback(userId, content, title));

		return null != result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/retrieve-feedbacks")
	public List<Feedback> getFeedbacks() {
		return service.getFeedbacks();
	}
}
