package com.personal.healthyfoodandbeverages.service;

import com.personal.healthyfoodandbeverages.dto.request.CustomerRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchCustomerRequest;
import com.personal.healthyfoodandbeverages.dto.response.CustomerResponse;
import com.personal.healthyfoodandbeverages.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createNewCustomer (CustomerRequest customerRequest);

    Customer getById (String customerId);

    List<CustomerResponse> getAllCustomers (SearchCustomerRequest customerRequest);

    CustomerResponse updateMembershipById (String customerId, String newMembershipId);

    CustomerResponse deleteById (String customerId);

    void updatePoinById(String customerId);

}
