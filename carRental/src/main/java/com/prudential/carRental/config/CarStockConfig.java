package com.prudential.carRental.config;

import com.prudential.carRental.dao.CarStockEntityDAO;
import com.prudential.carRental.entity.CarStockEntity;
import com.prudential.carRental.utils.DateUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 初始化汽车的库存信息，用于模拟数据库数据
 */
@Component
public class CarStockConfig {

    //每次初始化7天的数据，也就是只能预订7天内的车
    @Bean
    InitializingBean savedata(CarStockEntityDAO repo){
        return () -> {
            repo.save(new CarStockEntity(1, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-10")));
            repo.save(new CarStockEntity(2, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-11")));
            repo.save(new CarStockEntity(3, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-12")));
            repo.save(new CarStockEntity(4, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-13")));
            repo.save(new CarStockEntity(5, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-14")));
            repo.save(new CarStockEntity(6, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-15")));
            repo.save(new CarStockEntity(7, "Toyota Camry", 2, DateUtil.getFormatDate("2021-07-16")));

            repo.save(new CarStockEntity(8, "BMW 650", 2, DateUtil.getFormatDate("2021-07-10")));
            repo.save(new CarStockEntity(9, "BMW 650", 2, DateUtil.getFormatDate("2021-07-11")));
            repo.save(new CarStockEntity(10, "BMW 650", 2, DateUtil.getFormatDate("2021-07-12")));
            repo.save(new CarStockEntity(11, "BMW 650", 2, DateUtil.getFormatDate("2021-07-13")));
            repo.save(new CarStockEntity(12, "BMW 650", 2, DateUtil.getFormatDate("2021-07-14")));
            repo.save(new CarStockEntity(13, "BMW 650", 2, DateUtil.getFormatDate("2021-07-15")));
            repo.save(new CarStockEntity(14, "BMW 650", 2, DateUtil.getFormatDate("2021-07-16")));
        };
    }



}
