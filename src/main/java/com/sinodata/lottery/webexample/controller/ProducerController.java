package com.sinodata.lottery.webexample.controller;

import com.sinodata.lottery.webexample.core.ApiResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka/")
@Api(value = "kafka producer api", description = "kafka producer api")
public class ProducerController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("send")
    @ApiOperation(notes = "使用kafka模板发送信息", value = "send")
    public ApiResp<String>  send(String msg) {
        kafkaTemplate.send("demo", msg); //使用kafka模板发送信息
        return  ApiResp.ok("success");
    }
}
