package com.prudential.carRental.service.impl;

import com.prudential.carRental.dao.CarStockEntityDAO;
import com.prudential.carRental.entity.CarModelEntity;
import com.prudential.carRental.entity.CarStockEntity;
import com.prudential.carRental.entity.QueryParamEntity;
import com.prudential.carRental.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("carRentalService")
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    CarStockEntityDAO carStockEntityDAO;

    @Override
    public List<CarModelEntity> queryCarModel() {
        List<CarModelEntity> list = new ArrayList<CarModelEntity>();

        List<String> all = carStockEntityDAO.findAllCarModel();
        for (String carModel : all) {
            list.add(new CarModelEntity(carModel));
        }

        return list;
    }

    @Override
    public List<CarStockEntity> queryCarStock(QueryParamEntity queryParamEntity) {
        List<Object[]> carByCondition = carStockEntityDAO.findCarByCondition(queryParamEntity.getCarModel(), queryParamEntity.getStartDate(), queryParamEntity.getEndDate());
        List<CarStockEntity> list = new ArrayList<CarStockEntity>();
        for (Object[] obj : carByCondition) {
            list.add(new CarStockEntity((int) obj[0], (String) obj[1], (int) obj[2], (Date) obj[3]));
        }
        return list;
    }

    @Override
    public boolean updateCar(QueryParamEntity queryParamEntity, String type) {
        try {
            List<CarStockEntity> carStockEntities = null;
            if("booking".equals(type)){
                carStockEntities = this.queryCarStock(queryParamEntity);
            }else if("cancel".equals(type)){
                carStockEntities = this.queryRentalCar(queryParamEntity);
            }else {
                return false;
            }

            for (CarStockEntity carStock : carStockEntities) {
                if("booking".equals(type)){
                    carStockEntityDAO.updateCarStock(carStock.getCarModel(), carStock.getTime(), carStock.getStock() - 1);
                }else if("cancel".equals(type)){
                    carStockEntityDAO.updateCarStock(carStock.getCarModel(), carStock.getTime(), carStock.getStock() + 1);
                }else {
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CarStockEntity> queryRentalCar(QueryParamEntity queryParamEntity) {
        List<Object[]> carByCondition = carStockEntityDAO.findRentalCarByCondition(queryParamEntity.getCarModel(), queryParamEntity.getStartDate(), queryParamEntity.getEndDate());
        List<CarStockEntity> list = new ArrayList<CarStockEntity>();
        for (Object[] obj : carByCondition) {
            list.add(new CarStockEntity((int) obj[0], (String) obj[1], (int) obj[2], (Date) obj[3]));
        }
        return list;
    }

}
