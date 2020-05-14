package com.sinodata.lottery.webexample.service;

import com.sinodata.lottery.webexample.core.ApiResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {
    public static Logger logger = LoggerFactory.getLogger(RestClientService.class);

    @Autowired
    private RestTemplateBuilder builder;

    @Value("${restsvc.url}")
    String url;

    @Bean
    public RestTemplate restTemplate(){
        return builder.rootUri(url).build();
    }

    public Object gameBuilder(Long id){
        ApiResp<Object> apiResp = restTemplate().getForObject("/game/byId/" + id, ApiResp.class);
        logger.info(apiResp.getData().toString());
        return apiResp.getData();
    }
}
