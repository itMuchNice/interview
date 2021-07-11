package com.prudential.carRental.controller;


import com.alibaba.fastjson.JSON;
import com.prudential.carRental.dto.ResultDTO;
import com.prudential.carRental.entity.CarModelEntity;
import com.prudential.carRental.entity.CarStockEntity;
import com.prudential.carRental.entity.QueryParamEntity;
import com.prudential.carRental.service.CarRentalService;
import com.prudential.carRental.utils.BindingResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CarRental4UserController {

    @Autowired
    private CarRentalService carRentalService;

    @ApiOperation(value = "查询所有的汽车型号", response = ResultDTO.class)
    @GetMapping("/queryCarModelList")
    public ResultDTO queryCarModelList() {
        List<CarModelEntity> carModelEntities = this.carRentalService.queryCarModel();
        return new ResultDTO("0", "查询成功", JSON.toJSONString(carModelEntities));
    }


    @ApiOperation(value = "查询具体车辆的可租日期", response = ResultDTO.class)
    @PostMapping("/queryCarStock")
    public ResultDTO queryCarStock(@RequestBody @Valid QueryParamEntity queryParamEntity, BindingResult bindingResult) {
        //参数检查
        ResultDTO errorMessage = checkParams(queryParamEntity, bindingResult);
        if (errorMessage != null) return errorMessage;

        List<CarStockEntity> carStockEntities = this.carRentalService.queryCarStock(queryParamEntity);

        return new ResultDTO("0", "查询成功", JSON.toJSONString(carStockEntities));
    }


    @ApiOperation(value = "预订车辆", response = ResultDTO.class)
    @PostMapping("/bookingCar")
    public ResultDTO bookingCar(@RequestBody @Valid QueryParamEntity queryParamEntity, BindingResult bindingResult) {
        //参数检查
        ResultDTO errorMessage = checkParams(queryParamEntity, bindingResult);
        if (errorMessage != null) return errorMessage;

        List<CarStockEntity> carStockEntities = this.carRentalService.queryCarStock(queryParamEntity);
        if(null == carStockEntities || carStockEntities.size() == 0){
            return new ResultDTO("2002", "没有可预订的车辆", JSON.toJSONString(queryParamEntity));
        }

        boolean bookingResult =  this.carRentalService.updateCar(queryParamEntity, "booking");
        if(bookingResult){
            return new ResultDTO("0", "预订成功", JSON.toJSONString(queryParamEntity));
        }else{
            return new ResultDTO("2001", "预订失败，请重新查询", JSON.toJSONString(queryParamEntity));
        }
    }

    @ApiOperation(value = "取消预订车辆", response = ResultDTO.class)
    @PostMapping("/cancelBookingCar")
    public ResultDTO giveBackCar(@RequestBody @Valid QueryParamEntity queryParamEntity, BindingResult bindingResult) {
        //参数检查
        ResultDTO errorMessage = checkParams(queryParamEntity, bindingResult);
        if (errorMessage != null) return errorMessage;

        List<CarStockEntity> rentalCarStockEntities = this.carRentalService.queryRentalCar(queryParamEntity);
        if(null == rentalCarStockEntities || rentalCarStockEntities.size() == 0){
            return new ResultDTO("2002", "该车辆尚未预订，不可取消", JSON.toJSONString(queryParamEntity));
        }

        boolean giveBackCarResult =  this.carRentalService.updateCar(queryParamEntity, "cancel");
        if(giveBackCarResult){
            return new ResultDTO("0", "取消成功", JSON.toJSONString(queryParamEntity));
        }else{
            return new ResultDTO("2001", "取消失败，请重新查询", JSON.toJSONString(queryParamEntity));
        }
    }




    //参数检查
    private ResultDTO checkParams(QueryParamEntity queryParamEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            String errorMessage = BindingResultUtil.processBindingResult(bindingResult);
            return new ResultDTO("1001", "参数有误", errorMessage);
        }

        if(queryParamEntity.getStartDate().after(queryParamEntity.getEndDate())){
            return new ResultDTO("1002", "参数有误,开始时间不能大于结束时间", "");
        }
        return null;
    }


}
