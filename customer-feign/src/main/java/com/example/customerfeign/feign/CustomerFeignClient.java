package com.example.customerfeign.feign;

import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Set;

import static com.example.customerfeign.feign.LoadBalancerUtils.buildRequestData;
import static com.example.customerfeign.feign.LoadBalancerUtils.executeWithLoadBalancerLifecycleProcessing;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/1/26 10:54
 */
@Slf4j
public class CustomerFeignClient extends FeignBlockingLoadBalancerClient {
    private final Client delegate;
    private final LoadBalancerClient loadBalancerClient;
    private final LoadBalancerProperties properties;
    private final LoadBalancerClientFactory loadBalancerClientFactory;
    private final MockProperties mockProperties;

    public CustomerFeignClient(Client delegate, LoadBalancerClient loadBalancerClient, LoadBalancerProperties properties,
                               LoadBalancerClientFactory loadBalancerClientFactory, MockProperties mockProperties) {
        super(delegate, loadBalancerClient, properties, loadBalancerClientFactory);
        this.delegate = delegate;
        this.loadBalancerClient = loadBalancerClient;
        this.properties = properties;
        this.loadBalancerClientFactory = loadBalancerClientFactory;
        this.mockProperties = mockProperties;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        //do action..
        String url = request.url();

        final URI originalUri = URI.create(url);
        String serviceId = originalUri.getHost();

        String api = mockProperties.getMockApi(serviceId);
        Collection<String> strings = request.headers().get(MockProperties.MOCK_HEADER);
        boolean flag = !CollectionUtils.isEmpty(strings) && strings.contains("true");

        if (StringUtils.isNotBlank(api) && flag) {
            String newRequestUrl = url.replaceFirst(serviceId, api);
            //重新构建 request　对象
            Request newRequest = Request.create(request.httpMethod(),
                    newRequestUrl, request.headers(), request.body(),
                    request.charset(), null);
            //do action..
            return delegate.execute(newRequest, options);
        }
        return originExecute(request, options);
    }

    public Response originExecute(Request request, Request.Options options) throws IOException {
        final URI originalUri = URI.create(request.url());
        String serviceId = originalUri.getHost();
        Assert.state(serviceId != null, "Request URI does not contain a valid hostname: " + originalUri);
        String hint = getHint(serviceId);
        DefaultRequest<RequestDataContext> lbRequest = new DefaultRequest<>(
                new RequestDataContext(buildRequestData(request), hint));
        Set<LoadBalancerLifecycle> supportedLifecycleProcessors = LoadBalancerLifecycleValidator
                .getSupportedLifecycleProcessors(
                        loadBalancerClientFactory.getInstances(serviceId, LoadBalancerLifecycle.class),
                        RequestDataContext.class, ResponseData.class, ServiceInstance.class);
        supportedLifecycleProcessors.forEach(lifecycle -> lifecycle.onStart(lbRequest));
        ServiceInstance instance = loadBalancerClient.choose(serviceId, lbRequest);
        org.springframework.cloud.client.loadbalancer.Response<ServiceInstance> lbResponse = new DefaultResponse(
                instance);
        if (instance == null) {
            String message = "Load balancer does not contain an instance for the service " + serviceId;

            supportedLifecycleProcessors.forEach(lifecycle -> lifecycle
                    .onComplete(new CompletionContext<ResponseData, ServiceInstance, RequestDataContext>(
                            CompletionContext.Status.DISCARD, lbRequest, lbResponse)));
            return Response.builder().request(request).status(HttpStatus.SERVICE_UNAVAILABLE.value())
                    .body(message, StandardCharsets.UTF_8).build();
        }
        String reconstructedUrl = loadBalancerClient.reconstructURI(instance, originalUri).toString();
        Request newRequest = buildRequest(request, reconstructedUrl);
        return executeWithLoadBalancerLifecycleProcessing(delegate, options, newRequest, lbRequest, lbResponse,
                supportedLifecycleProcessors);
    }

    private String getHint(String serviceId) {
        String defaultHint = properties.getHint().getOrDefault("default", "default");
        String hintPropertyValue = properties.getHint().get(serviceId);
        return hintPropertyValue != null ? hintPropertyValue : defaultHint;
    }
}
