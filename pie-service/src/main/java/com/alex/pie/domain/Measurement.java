package com.alex.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    private String id;
    private Double temperature;
    private Double humidity;
    private String location;
    private LocalDateTime createdDate;
}
