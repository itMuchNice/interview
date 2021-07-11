package com.prudential.carRental.service;

import com.prudential.carRental.dao.CarStockEntityDAO;
import com.prudential.carRental.entity.CarModelEntity;
import com.prudential.carRental.entity.CarStockEntity;
import com.prudential.carRental.entity.QueryParamEntity;
import com.prudential.carRental.service.impl.CarRentalServiceImpl;
import com.prudential.carRental.utils.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarRentalServiceImplTest {

    @Mock
    private CarStockEntityDAO carStockEntityDAO;

    @InjectMocks
    private CarRentalServiceImpl carRentalService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryCarModelTest() {
        List<String> list = new ArrayList<String>();
        list.add("abc");
        when(carStockEntityDAO.findAllCarModel()).thenReturn(list);
        List<CarModelEntity> carModelEntities = carRentalService.queryCarModel();
        Assert.assertTrue(carModelEntities.get(0).getCarModel().equals("abc"));
    }

    @Test
    public void queryCarModelWithQueryNull() {
        List<CarModelEntity> carModelEntities = carRentalService.queryCarModel();
        Assert.assertTrue(carModelEntities.size() == 0);
    }

    @Test
    public void queryCarStockTest() {
        List<Object[]> list = new ArrayList<Object[]>();
        Object[] objects = {1, "BMW 650", 2, DateUtil.getFormatDate("2021-07-11")};
        list.add(objects);

        when(carStockEntityDAO.findCarByCondition("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"))).thenReturn(list);

        QueryParamEntity queryParamEntity = new QueryParamEntity("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"));

        List<CarStockEntity> carStockEntities = carRentalService.queryCarStock(queryParamEntity);
        Assert.assertTrue(carStockEntities.get(0).getCarModel().equals("BMW 650"));
    }


    @Test
    public void updateCarTestWithBooking() {
        QueryParamEntity queryParamEntity = new QueryParamEntity("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"));
        boolean booking = carRentalService.updateCar(queryParamEntity, "booking");
        Assert.assertTrue(booking);
    }

    @Test
    public void updateCarTestWithCancel() {
        QueryParamEntity queryParamEntity = new QueryParamEntity("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"));
        boolean booking = carRentalService.updateCar(queryParamEntity, "cancel");
        Assert.assertTrue(booking);
    }

    @Test
    public void updateCarTestWithOther() {
        QueryParamEntity queryParamEntity = new QueryParamEntity("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"));
        boolean booking = carRentalService.updateCar(queryParamEntity, "other");
        Assert.assertFalse(booking);
    }

    @Test
    public void queryRentalCarTest() {
        List<Object[]> list = new ArrayList<Object[]>();
        Object[] objects = {1, "BMW 650", 2, DateUtil.getFormatDate("2021-07-11")};
        list.add(objects);

        when(carStockEntityDAO.findRentalCarByCondition("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"))).thenReturn(list);

        QueryParamEntity queryParamEntity = new QueryParamEntity("BMW 650",
                DateUtil.getFormatDate("2021-07-11"),
                DateUtil.getFormatDate("2021-07-11"));

        List<CarStockEntity> carStockEntities = carRentalService.queryRentalCar(queryParamEntity);
        Assert.assertTrue(carStockEntities.get(0).getCarModel().equals("BMW 650"));
    }
}
