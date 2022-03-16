package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.service.PlayHistorytService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2022/3/15 16:04
 * @description 播放记录Controller
 */
@RestController
@RequestMapping("/playHistory")
public class PlayHistoryController {

    @Autowired
    private PlayHistorytService playHistorytService;

    /**
     * 根据userId查询用户的播放记录
     */
    @GetMapping("/getHistoryByUserId")
    public Map getHistoryByUserId(Integer userId){
        return playHistorytService.getHistoryByUserId(userId);
    }

    /**
     * 根据userId和songId生成播放记录
     */
    @GetMapping("/createPlayHistory")
    public void createPlayHistory(Integer userId, Integer songId){
        playHistorytService.createPlayHistory(userId, songId);
    }
}
