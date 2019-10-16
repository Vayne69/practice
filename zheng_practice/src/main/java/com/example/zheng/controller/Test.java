package com.example.zheng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Yang Jian
 * @date : 2019/10/15 16:25
 */
public class Test {
    public static void main(String[] args) {

        //假设以下字段映射的是User类
        Map<String, Object> map = new HashMap<>();
        map.put("age1", "@age");
        // map.put("age2", 15);
        // map.put("age3", new Integer[]{1,2,3,4});
        map.put("name1", "@cname");
        // map.put("name2", "@title(2)");
        // map.put("name3", "这是一个名字");

        //下面三个email字段的参数，如果是中文，必须放在单引号或双引号中才会生效，英文不受限制
        map.put("email1", "@email('这是中文')");
        // map.put("email2", "@email('this is english')");
        // map.put("email3", "@email(this is english)");
        //

        //记录映射
        // Mock.set(User.class, map);
        Mock.set("userMap", map);
        //User类的映射被直接记录，可以获取
        // MockObject<User> userMockObject = Mock.get(User.class);
        MockObject userMockObject = Mock.get("userMap");

        JSONObject jsonObject = new JSONObject();
        String s = "{\n" +
                "  \"string|1-2\": \"@string\",\n" +
                "  \"integer\": \"@integer(10, 30)\",\n" +
                "  \"float\": \"@float(60, 100, 2, 2)\",\n" +
                "  \"boolean\": \"@boolean\",\n" +
                "  \"date\": \"@toDateStr\",\n" +
                "  \"datetime\": \"@time\",\n" +
                "  \"now\": \"@toDateTime\",\n" +
                "  \"url\": \"@url\",\n" +
                "  \"email\": \"@email\",\n" +
                "  \"region\": \"@region\",\n" +
                "  \"city\": \"@city\",\n" +
                "  \"province\": \"@province\",\n" +
                "  \"county\": \"@county\",\n" +
                "  \"upper\": \"@upper(@title)\",\n" +
                "  \"guid\": \"@guid\",\n" +
                "  \"id\": \"@id\",\n" +
                "  \"image\": \"@image(200x200)\",\n" +
                "  \"title\": \"@title\",\n" +
                "  \"cparagraph\": \"@cparagraph\",\n" +
                "  \"csentence\": \"@csentence\",\n" +
                "  \"range\": \"@range(2, 10)\"\n" +
                "}";
        Map<String, Object> map1 = JSON.parseObject(s);
        Mock.set("userMap1", map1);
        MockObject map12 = Mock.get("userMap1");
        System.out.println(map12.getOne());
        // userMockObject.getStream(3).forEach(System.out::println);
        // System.out.println(userMockObject.getOne());
    }
}
