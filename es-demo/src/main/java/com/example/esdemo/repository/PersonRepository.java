package com.example.esdemo.repository;

import com.example.esdemo.model.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/2/16 11:09
 */
public interface PersonRepository extends ElasticsearchRepository<Person, Long> {
    /**
     * 根据年龄区间查询
     *
     * @param min 最小值
     * @param max 最大值
     * @return 满足条件的用户列表
     */
    List<Person> findByAgeBetween(Integer min, Integer max);

    /**
     * 根据country查询
     *
     * @param country country
     * @return 满足条件的用户列表
     */
    List<Person> findByCountry(String country);
}
