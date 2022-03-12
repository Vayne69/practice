package com.example.customerfeign.feign;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "zlzn.mock")
public class MockProperties {
    public final static String MOCK_HEADER = "mockEnable";

    private boolean enabled;

    private List<String> apis;

    private Map<String, String> apiMaps = new HashMap<>();

    @PostConstruct
    private void init() {
        if (!CollectionUtils.isEmpty(apis)) {
            apiMaps = apis.stream().filter(s -> s.split("##").length == 2).collect(
                    Collectors.toMap(s -> s.split("##")[0], s -> s.split("##")[1]));
        }
    }

    public String getMockApi(String resource) {
        if (CollectionUtils.isEmpty(apiMaps)) {
            return null;
        }
        return apiMaps.get(resource);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getApis() {
        return apis;
    }

    public void setApis(List<String> apis) {
        this.apis = apis;
    }
}
