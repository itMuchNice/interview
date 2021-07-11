package com.prudential.carRental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "查询汽车库存参数")
public class QueryParamEntity {
    @NotEmpty(message = "汽车模型为空")
    @ApiModelProperty(value = "汽车型号", name="carModel", example = "BMW 650", required = true)
    private String carModel;

    @NotNull(message = "租赁开始时间为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "租赁开始时间", name="startDate", example = "2021-07-10", required = true)
    private Date startDate;

    @NotNull(message = "租赁结束时间为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "租赁结束时间", name="endDate", example = "2021-07-14", required = true)
    private Date endDate;

}
