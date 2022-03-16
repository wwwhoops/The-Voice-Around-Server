package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.Collect;
import com.theVoiceAround.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 23:08
 * @description 收藏Controller
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     */
    @PostMapping("/addCollect")
    public Map addCollect(@RequestBody Collect collect){
        return collectService.addCollect(collect);
    }

    /**
     * 查询歌曲是否已被收藏
     */
    @GetMapping("/getACollect")
    public String getACollect(Integer userId, Integer songId){
        return collectService.getACollect(userId, songId);
    }

    /**
     * 根据用户id查询收藏列表
     */
    @GetMapping("/getCollectByUserId")
    public Map getCollectByUserId(Integer userId){
        return collectService.getCollectByUserId(userId);
    }

    /**
     * 根据userId和songId删除一条收藏记录
     */
    @GetMapping("/deleteACollect")
    public Map deleteACollect(Integer userId, Integer songId){
        return collectService.deleteACollect(userId, songId);
    }

}
