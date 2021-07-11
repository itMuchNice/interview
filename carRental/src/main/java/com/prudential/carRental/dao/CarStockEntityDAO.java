package com.prudential.carRental.dao;

import com.prudential.carRental.entity.CarStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface CarStockEntityDAO extends JpaRepository<CarStockEntity, Integer> {

    @Query(value = "select DISTINCT carModel from CarStockEntity")
    List<String> findAllCarModel();

    //查询剩余库存情况
    @Query(value = "select id, carModel, stock, time from CarStockEntity where carModel = :carModel " +
            "and time >= :startDate and time <= :endDate and stock > 0")
    List<Object[]> findCarByCondition(@Param("carModel") String carModel,
                                      @Param("startDate") Date startDate,
                                      @Param("endDate") Date endDate);

    //查询已租车辆情况
    @Query(value = "select id, carModel, stock, time from CarStockEntity where carModel = :carModel " +
            "and time >= :startDate and time <= :endDate and stock < 2")
    List<Object[]> findRentalCarByCondition(@Param("carModel") String carModel,
                                      @Param("startDate") Date startDate,
                                      @Param("endDate") Date endDate);

    @Transactional
    @Modifying
    @Query(value = "update CarStockEntity c set c.stock = ?3 where c.carModel = ?1 and c.time = ?2")
    public int updateCarStock(String carModel,
                              Date time,
                              int stock);


}
