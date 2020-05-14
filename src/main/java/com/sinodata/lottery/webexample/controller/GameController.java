package com.sinodata.lottery.webexample.controller;

import com.sinodata.lottery.webexample.core.ApiResp;
import com.sinodata.lottery.webexample.domain.Game;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.sinodata.lottery.webexample.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@Api(value = "游戏")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/addGame")
    @ApiOperation(notes = "测试", value = "addGame")
    public ApiResp<Object> addGame(@RequestBody Game game) {
        gameService.addGame(game);
        return ApiResp.ok(game);
    }

    @GetMapping("/byId")
    @ApiOperation(notes = "测试", value = "byId")
    public com.sinodata.lottery.webexample.core.ApiResp<Object> getGameById(@RequestParam(defaultValue = "1") Long id) {
        return com.sinodata.lottery.webexample.core.ApiResp.ok(gameService.findById(id));
    }

    @GetMapping("/byId/{id}")
    @ApiOperation(notes = "测试", value = "byIdWithPath")
    public com.sinodata.lottery.webexample.core.ApiResp<Object> getGameByIdWithPath(@PathVariable("id") Long id) {
        return com.sinodata.lottery.webexample.core.ApiResp.ok(gameService.findById(id));
    }

    @PutMapping("/updateById")
    @ApiOperation(notes = "测试", value = "updateById")
    public com.sinodata.lottery.webexample.core.ApiResp<Integer> updateGameById(@RequestParam(defaultValue = "1") Long id,
                                                                                @RequestParam String name) {
       return com.sinodata.lottery.webexample.core.ApiResp.ok(gameService.updateById(id, name));
    }

    @GetMapping("/queryGamesForPage")
    @ApiOperation(notes = "测试", value = "queryGamesForPage")
    public ApiResp<Object> queryGamesForPage(Integer current, Integer size) {
        return ApiResp.ok(gameService.queryGamesForPage(current, size));
    }
}
