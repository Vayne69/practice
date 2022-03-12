package com.example.customerfeign.config;

import com.example.customerfeign.feign.CustomerFeignClient;
import com.example.customerfeign.feign.MockProperties;
import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.OnRetryNotEnabledCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

@Configuration
public class CustomerFeignConfig {
    @Autowired
    private MockProperties mockProperties;

    @Bean
    @Conditional({OnRetryNotEnabledCondition.class})
    @ConditionalOnProperty(name = "zlzn.mock.enabled", havingValue = "true")
    public Client feignClient(LoadBalancerClient loadBalancerClient, LoadBalancerProperties properties,
                              LoadBalancerClientFactory loadBalancerClientFactory) {
        return new CustomerFeignClient(new Client.Default((SSLSocketFactory) null, (HostnameVerifier) null),
                loadBalancerClient, properties, loadBalancerClientFactory, mockProperties);
    }

}
