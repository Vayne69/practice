package com.example.excledemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/3/11 10:51
 */
@Data
public class User {
    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private int age;
    @ExcelProperty(index = 2)
    private String sex;
}
