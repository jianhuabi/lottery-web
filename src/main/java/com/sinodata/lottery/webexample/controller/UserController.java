
package com.sinodata.lottery.webexample.controller;

import com.sinodata.lottery.webexample.core.ApiResp;
//import com.sinodata.lottery.webexample.service.RedisService;
import com.sinodata.lottery.webexample.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/user")
@Api(value = "用户", description = "用户api")
public class UserController {

    @Autowired
    private UserInfoService grpcClientService;

    //@Autowired
    //private RedisService redisService;

    @GetMapping("/byName")
    @ApiOperation(notes = "测试", value = "byName")
    public ApiResp<Object> getUserByName(@RequestParam(defaultValue = "Michael") String name) {
        return ApiResp.ok(grpcClientService.getUserByName(name));
    }

    @GetMapping("/list")
    @ApiOperation(notes = "测试", value = "list")
    public ApiResp<Object> getUserList() {
        return ApiResp.ok(grpcClientService.getUserList());
    }

    @GetMapping("/getAuthCode")
    @ApiOperation(notes = "测试", value = "getAuthCode")
    public ApiResp<Object> getAuthCode(@RequestParam(defaultValue = "13485041966")String telephone) {
        //generateAuthCode
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }

        String key = "AuthCode" + ":" + telephone;
        //redisService.set(key, sb.toString(),90);

        //return ApiResp.ok(redisService.get(key));
        return null;
    }

}
