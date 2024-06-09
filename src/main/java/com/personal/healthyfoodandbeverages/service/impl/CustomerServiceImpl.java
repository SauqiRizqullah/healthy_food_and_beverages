package com.personal.healthyfoodandbeverages.service.impl;

import com.personal.healthyfoodandbeverages.dto.request.CustomerRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchCustomerRequest;
import com.personal.healthyfoodandbeverages.dto.response.CustomerResponse;
import com.personal.healthyfoodandbeverages.entity.Customer;
import com.personal.healthyfoodandbeverages.entity.Membership;
import com.personal.healthyfoodandbeverages.repository.CustomerRepository;
import com.personal.healthyfoodandbeverages.service.CustomerService;
import com.personal.healthyfoodandbeverages.service.MembershipService;
import com.personal.healthyfoodandbeverages.specification.CustomerSpecification;
import com.personal.healthyfoodandbeverages.specification.MembershipSpecification;
import com.personal.healthyfoodandbeverages.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MembershipService membershipService;

    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse createNewCustomer(CustomerRequest customerRequest) {
        validationUtil.validate(customerRequest);
        Membership membership = membershipService.getMembershipbyId(customerRequest.getMembershipId());

        validationUtil.validate(membership);
        Customer customer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .mobilePhoneNo(customerRequest.getMobilePhoneNo())
                .membership(membership)
                .poin(customerRequest.getPoin())
                .build();

        if (customer.getPoin() != 0) {
            throw new IllegalArgumentException("New customer's poin started by zero");
        }

        customerRepository.saveAndFlush(customer);

        return parseCustomerToCustomerResponse(customer);
    }

    private CustomerResponse parseCustomerToCustomerResponse(Customer customer) {
        String id;
        if (customer.getCustomerId() == null) {
            id = null;
        } else {
            id = customer.getCustomerId();
        }

        return CustomerResponse.builder()
                .customerId(id)
                .customerName(customer.getCustomerName())
                .mobilePhoneNo(customer.getMobilePhoneNo())
                .membershipId(customer.getMembership().getMembershipId())
                .poin(customer.getPoin())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getById(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        return parseCustomerToCustomerResponse(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> getAllCustomers(SearchCustomerRequest customerRequest) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerRequest);
        if (customerRequest.getCustomerName() == null){
            return customerRepository.findAll().stream().map(this::parseCustomerToCustomerResponse).toList();
        } else
            return customerRepository.findAll(customerSpecification).stream().map(this::parseCustomerToCustomerResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse updateMembershipById(String customerId, String newMembershipId) {
        Membership membership = membershipService.getMembershipbyId(newMembershipId);

        validationUtil.validate(membership);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        customer.setMembership(membership);

        validationUtil.validate(customer);


        customerRepository.updateMembershipById(customerId, newMembershipId);
        customerRepository.saveAndFlush(customer);
        return parseCustomerToCustomerResponse(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse deleteById(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id was not there"));
        customerRepository.delete(customer);
        return parseCustomerToCustomerResponse(customer);
    }
}
