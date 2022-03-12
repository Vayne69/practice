package com.example.esdemo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.example.esdemo.model.Person;
import com.example.esdemo.repository.PersonRepository;
import com.example.esdemo.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class EsDemoApplication implements CommandLineRunner {
    @Autowired
    private PeopleService peopleService;

    public static void main(String[] args) {
        SpringApplication.run(EsDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        peopleService.customSelectByCountry("蜀国 ");
        peopleService.save();
    }

}
