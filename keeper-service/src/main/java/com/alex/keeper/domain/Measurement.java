package com.alex.keeper.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "measurements")
public class Measurement {

    @Id
    private String id;
    private Double temperature;
    private Double humidity;
    private String location;
    @NotNull
    private LocalDateTime createdDate;
}
