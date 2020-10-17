package com.alex.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    private Double temperature;
    private Double humidity;

    @CreatedDate
    private LocalDateTime createdDate;
}
