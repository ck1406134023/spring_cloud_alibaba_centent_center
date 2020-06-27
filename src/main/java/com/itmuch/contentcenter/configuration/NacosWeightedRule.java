package com.itmuch.contentcenter.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;

import java.util.List;

//public class NacosWeightedRule implements IRule {
//    @Override
//    public Server choose(Object key) {
//        return null;
//    }
//
//    @Override
//    public void setLoadBalancer(ILoadBalancer lb) {
//
//    }
//
//    @Override
//    public ILoadBalancer getLoadBalancer() {
//        return null;
//    }
//}

public class NacosWeightedRule extends AbstractLoadBalancerRule {


    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        //读取配置文件，并初始化 AbstractLoadBalancerRule
    }

    @Override
    public Server choose(Object key) {
        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
        String name=loadBalancer.getName();
        return null;
    }
}

