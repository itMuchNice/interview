package com.prudential.carRental.service;

import com.prudential.carRental.entity.CarModelEntity;
import com.prudential.carRental.entity.CarStockEntity;
import com.prudential.carRental.entity.QueryParamEntity;

import java.util.List;

public interface CarRentalService {
    public List<CarModelEntity> queryCarModel();

    public List<CarStockEntity> queryCarStock(QueryParamEntity queryParamEntity);

    public boolean updateCar(QueryParamEntity queryParamEntity, String type);

    public List<CarStockEntity> queryRentalCar(QueryParamEntity queryParamEntity);
}
