package com.personal.healthyfoodandbeverages.controller;

import com.personal.healthyfoodandbeverages.constant.APIUrl;
import com.personal.healthyfoodandbeverages.dto.request.CustomerRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchCustomerRequest;
import com.personal.healthyfoodandbeverages.dto.response.CommonResponse;
import com.personal.healthyfoodandbeverages.dto.response.CustomerResponse;
import com.personal.healthyfoodandbeverages.entity.Customer;
import com.personal.healthyfoodandbeverages.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<CustomerResponse>> createNewCustomer (
            @RequestBody CustomerRequest customerRequest
            ){
        CustomerResponse newCustomer = customerService.createNewCustomer(customerRequest);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("New customer data has been created")
                .data(newCustomer)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<CustomerResponse>> getById (
            @PathVariable String customerId
    ) {
        CustomerResponse customerResponse = customerService.getById(customerId);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Here's the data you wanted")
                .data(customerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomers (
            @RequestParam(name = "customerName", required = false) String customerName
    ) {
        SearchCustomerRequest customerRequest = SearchCustomerRequest.builder()
                .customerName(customerName)
                .build();

        List<CustomerResponse> allCustomers = customerService.getAllCustomers(customerRequest);

        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's all customers data")
                .data(allCustomers)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<CustomerResponse>> updateMembershipById (
            @PathVariable String customerId,
            @RequestParam(name = "membershipId") String newMembershipId
    ){
        CustomerResponse customer = customerService.updateMembershipById(customerId, newMembershipId);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(customerId + "'s membership data has been updated")
                .data(customer)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID,produces = "application/json")
    public ResponseEntity<CommonResponse<CustomerResponse>> deleteById (
            @PathVariable String customerId
    ) {
        CustomerResponse customerResponse = customerService.deleteById(customerId);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(customerId + "'s data has been deleted")
                .data(customerResponse)
                .build();

        return ResponseEntity.ok(response);
    }

}
