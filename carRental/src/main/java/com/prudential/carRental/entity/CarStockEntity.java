package com.prudential.carRental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 汽车库存实体类，可展示每天的库存信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarStockEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String carModel;
    private int stock;
    private Date time;

}
