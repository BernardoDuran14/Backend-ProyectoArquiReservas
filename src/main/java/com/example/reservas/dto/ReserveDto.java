package com.example.reservas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDto {

    private Long id;
    private Long customerId;
    private Long spaceId;
    private Long employeeId;
    private String vehiclePlate;
    private Boolean def;
    private Boolean status;
    private Date startDate;
    private Date endDate;
    private int startTime;
    private int endTime;
}
