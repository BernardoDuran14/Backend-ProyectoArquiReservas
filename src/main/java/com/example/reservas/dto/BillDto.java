package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_bill_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_bill_id")})
    private Long id;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_bill_total")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_bill_total")})
    private Double total;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_bill_customer_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_bill_customer_id")})
    private Long customer;

//    @lombok.Getter(onMethod_ = {@JsonProperty("p_bill_employee_id")})
//    @lombok.Setter(onMethod_ = {@JsonProperty("p_bill_employee_id")})
    private Long employee;
}
