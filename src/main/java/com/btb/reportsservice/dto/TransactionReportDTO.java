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
public class TransactionReportDTO {

    private Long id;

    private Date createdAt;

    private Long transactionType;

    private Long quantity;

    private String asset;

    private Double priceUnit;

    private Long user;

}
