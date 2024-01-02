package com.btb.reportsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityReportDTO {

    private long id;

    private long userId;

    private String eventType;

    private Date atDatetime;

    private String description;

}
