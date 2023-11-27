package com.example.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private String message;
    private String type;
    private Date date;
}
