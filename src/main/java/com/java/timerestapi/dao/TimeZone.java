package com.java.timerestapi.dao;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor(staticName = "build")
public class TimeZone {

    private String abbreviation;
    private String timezone;
    private String datetime;
}
