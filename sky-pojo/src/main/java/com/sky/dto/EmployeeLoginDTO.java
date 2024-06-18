package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "data model for employee login")
public class EmployeeLoginDTO implements Serializable {

    @ApiModelProperty("user name")
    private String username;

    @ApiModelProperty("password")
    private String password;

}
