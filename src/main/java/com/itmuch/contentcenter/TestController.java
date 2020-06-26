package com.itmuch.contentcenter;

import com.itmuch.contentcenter.dao.content.ShareMapper;
import com.itmuch.contentcenter.domain.entity.content.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author chenkai
 */
@RestController
public class TestController {

    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/test")
    public List<Share> testUser(){
        //插入
        Share share = new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("xxxx");
        share.setCover("sss");
        share.setAuthor("ck");
        share.setBuyCount(1);
        this.shareMapper.insertSelective(share);
        //查询 查询当前数据库所有share

        List<Share> shares = this.shareMapper.selectAll();

        return shares;
    }

    /**
     * 测试：服务发现，证明内容中心总能找到用户中心
     * @return 用户中心所有实例的地址信息
     */
    @GetMapping("test2")
    public List<ServiceInstance> getInstances(){
        //  查询指定服务所有实例的信息
        return this.discoveryClient.getInstances("user-center");
    }
}
