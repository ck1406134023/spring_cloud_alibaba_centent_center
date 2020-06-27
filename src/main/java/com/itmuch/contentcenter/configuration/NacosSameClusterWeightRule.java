package com.itmuch.contentcenter.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;

public class NacosSameClusterWeightRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {

        //拿到配置名称
        String clusterName = nacosDiscoveryProperties.getClusterName();

        // 1.找到指定服务的所有服务
        // 2.过滤相同集群下的所有实例
        // 3.如果B是空，就用A
        // 4.基于权重的负载均衡算法，返回一个实例

        return null;
    }
}
