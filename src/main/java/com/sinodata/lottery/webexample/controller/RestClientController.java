package com.sinodata.lottery.webexample.controller;

import com.sinodata.lottery.webexample.core.ApiResp;
import com.sinodata.lottery.webexample.service.RestClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest_client/")
@Api(value = "rest client", description = "rest client")
public class RestClientController {
    @Autowired
    RestClientService restClientService;

    @GetMapping("getGame")
    @ApiOperation(notes = "从其他rest server获取Game", value = "getGame")
    public ApiResp<Object> getGame(Long id) {
        return  ApiResp.ok(restClientService.gameBuilder(id));
    }
}
