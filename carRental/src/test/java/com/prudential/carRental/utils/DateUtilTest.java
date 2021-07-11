package com.prudential.carRental.utils;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void getFormatDateWithNull(){
        Date formatDate = DateUtil.getFormatDate(null);
        Assert.assertEquals(null, formatDate);
    }

    @Test
    public void getFormatDateWithNumber(){
        Date formatDate = DateUtil.getFormatDate("123");
        Assert.assertEquals(null, formatDate);
    }

    @Test
    public void getFormatDateWithAbc(){
        Date formatDate = DateUtil.getFormatDate("abc");
        Assert.assertEquals(null, formatDate);
    }

    @Test
    public void getFormatDate(){
        Date formatDate = DateUtil.getFormatDate("2021-07-11");
        Assert.assertNotNull(formatDate);
    }
}
