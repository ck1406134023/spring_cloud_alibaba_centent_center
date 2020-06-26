package com.itmuch.contentcenter.service.content;

import com.itmuch.contentcenter.dao.content.ShareMapper;
import com.itmuch.contentcenter.domain.dto.content.ShareDTO;
import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import com.itmuch.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {
    private final ShareMapper shareMapper;
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    public ShareDTO findById(Integer id){

        //获取分享详情
        Share share=this.shareMapper.selectByPrimaryKey(id);
        //发布人id
        Integer userId = share.getUserId();
        //怎么调用用户微服务的users/userid
        //用户中心所有实例的信息
        List<ServiceInstance> instances=discoveryClient.getInstances("user-center");
        String targetUrl = instances.stream()
                //数据变换
                .map(instance->instance.getUri().toString()+"users/{id}" )
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("当前没有实例"));

        log.info("地址为:{}",targetUrl);
        ResponseEntity<UserDTO> userDTO = this.restTemplate.getForEntity(
                targetUrl,
                UserDTO.class,userId
        );
        //消息装配
        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share,shareDTO);
        shareDTO.setWxNickname(userDTO.getBody().getWxNickname());
        return shareDTO;

    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //用HTTP Get方法去请求，并且返回一个对象
//        String forObject = restTemplate.getForObject(
//                "http://localhost:8082/users/2",
//                String.class
//        );
//        String forObject = restTemplate.getForObject(
//                "http://localhost:8082/users/{id}",
//                String.class,1
//        );
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:8082/users/{id}",
                String.class,2
        );
    }
}


