package com.example.practice.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author : Yang Jian
 * @date : 2019/9/30 16:08
 */
public class BeanUtilsTest {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Date date = new Date();
        User user = new User();
        BigUser bigUser = new BigUser(111, "lis", "男", date);
        System.out.println("开始前" + user);
        System.out.println("开始前" + bigUser);
        BeanUtils.copyProperties(bigUser, user);
        // PropertyUtils.copyProperties(bigUser,user);
        System.out.println("开始后" + user);
        System.out.println("开始后" + bigUser);
    }

}

@Data
class User {
    private long age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date birth;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String addr;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BigUser {
    private Integer age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sex;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date birth;
}
