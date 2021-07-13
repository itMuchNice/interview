package com.prudential.carRental.controller;


import com.prudential.carRental.service.CarRentalService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CarRental4UserController.class, secure = false)
public class CarRental4UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRentalService carRentalService;

    //查询汽车模型的测试用例
    @Test
    public void getAllCarModel() throws Exception {
        mockMvc.perform(get("/user/queryCarModelList")).andDo(print()).andExpect(status().isOk());
    }

    //查询汽车库存的测试用例
    @Test
    public void queryCarStock() throws Exception {
        String json = "{\"carModel\":\"BMW 650\",\"startDate\":\"2021-07-10\",\"endDate\":\"2021-07-12\"}";
        MvcResult mvcResult = mockMvc.perform(
                post("/user/queryCarStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"0\",\"message\":\"查询成功\",\"resultData\":[]}", response.getContentAsString());

    }

    @Test
    public void queryCarStockWithNoCarModel() throws Exception {
        String json = "{\"carModel\":\"\",\"startDate\":\"2021-07-10\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/queryCarStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"1001\",\"message\":\"参数有误,汽车模型为空;\",\"resultData\":null}", response.getContentAsString());
    }

    @Test
    public void queryCarStockWithNoStartDate() throws Exception {
        String json = "{\"carModel\":\"BMW 650\",\"startDate\":\"\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/queryCarStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"1001\",\"message\":\"参数有误,租赁开始时间为空;\",\"resultData\":null}", response.getContentAsString());
    }

    @Test
    public void queryCarStockWithNoCarModelAndStartDate() throws Exception {
        String json = "{\"carModel\":\"\",\"startDate\":\"\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/queryCarStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertTrue(response.getContentAsString().contains("汽车模型为空;"));
        Assert.assertTrue(response.getContentAsString().contains("租赁开始时间为空;"));


    }

    @Test
    public void queryCarStockWithErrorTime() throws Exception {
        String json = "{\"carModel\":\"BMW 650\",\"startDate\":\"207-10\",\"endDate\":\"20-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/queryCarStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void queryCarStockWithStartTimeGreaterEndTime() throws Exception {
        String json = "{\"carModel\":\"BMW 650\",\"startDate\":\"2021-07-14\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/queryCarStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"1002\",\"message\":\"参数有误,开始时间不能大于结束时间\",\"resultData\":null}", response.getContentAsString());
    }

    //预订车辆的测试用例
    @Test
    public void bookingCarTestWithNoCar() throws Exception {
        String json = "{\"carModel\":\"BMW 650\",\"startDate\":\"2021-07-10\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/bookingCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"2002\",\"message\":\"没有可预订的车辆\",\"resultData\":{\"endDate\":\"2021-07-12T00:00:00.000+0000\",\"startDate\":\"2021-07-10T00:00:00.000+0000\",\"carModel\":\"BMW 650\"}}",
                response.getContentAsString());
    }

    @Test
    public void bookingCarWithNoCarModel() throws Exception {
        String json = "{\"carModel\":\"\",\"startDate\":\"2021-07-10\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/bookingCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"1001\",\"message\":\"参数有误,汽车模型为空;\",\"resultData\":null}", response.getContentAsString());
    }


    //取消预订车辆的测试用例
    @Test
    public void cancelBookingCar() throws Exception {
        String json = "{\"carModel\":\"BMW 650\",\"startDate\":\"2021-07-10\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/cancelBookingCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"3002\",\"message\":\"该车辆尚未预订，不可取消\",\"resultData\":{\"endDate\":\"2021-07-12T00:00:00.000+0000\",\"startDate\":\"2021-07-10T00:00:00.000+0000\",\"carModel\":\"BMW 650\"}}",
                response.getContentAsString());
    }

    @Test
    public void cancelBookingCarWithNoCarModel() throws Exception {
        String json = "{\"carModel\":\"\",\"startDate\":\"2021-07-10\",\"endDate\":\"2021-07-12\"}";

        MvcResult mvcResult = mockMvc.perform(
                post("/user/cancelBookingCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals("{\"returnCode\":\"1001\",\"message\":\"参数有误,汽车模型为空;\",\"resultData\":null}", response.getContentAsString());
    }


}
