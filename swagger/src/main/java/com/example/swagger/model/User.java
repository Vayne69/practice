package com.example.swagger.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yang Jian
 * @date : 2019/10/27 0027 10:30
 */
@ApiModel("用户实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户id")
    private int id;
}
