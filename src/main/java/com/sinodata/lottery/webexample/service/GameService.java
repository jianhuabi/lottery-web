package com.sinodata.lottery.webexample.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinodata.lottery.webexample.domain.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinodata.lottery.webexample.domain.Game;
import com.sinodata.lottery.webexample.domain.GameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameMapper gameMapper;

    public Game findById(Long id) {
        return  gameMapper.selectById(id);
//        return gameRepository.findById(id).get();
    }

    public Integer updateById(long id, String name){
        Optional<Game> game = gameRepository.findById(id);
        Game g = game.get();
        g.setName(name);
        return gameMapper.updateById(g);
    }

    public Game addGame(Game game){
//        Game game = new Game();
//        game.setId(id);
//        game.setName(name);
        gameMapper.insert(game);
        return game;
    }

    public Map<String, Object> queryGamesForPage(Integer current, Integer size) {
        Map<String, Object> result = new HashMap<>();
        IPage<Game> page = new Page<>(current, size);
        gameMapper.selectPage(page, null);
        result.put("currentPage", current);
        result.put("pageSize", size);
        result.put("total", page.getTotal());
        result.put("data", page.getRecords());
        return result;
    }

}
