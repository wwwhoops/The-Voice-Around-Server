package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.theVoiceAround.music.entity.PlayHistory;
import com.theVoiceAround.music.mapper.PlayHistoryMapper;
import com.theVoiceAround.music.service.PlayHistorytService;
import com.theVoiceAround.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 23:10
 * @description 播放记录Service实现类
 */
@Service
public class PlayHistoryServiceImpl implements PlayHistorytService {

    @Autowired
    private PlayHistoryMapper playHistoryMapper;

    @Override
    public Map getHistoryByUserId(Integer userId) {
        Map map = new HashMap();
        if(userId != null){
            List resultList = playHistoryMapper.getHistoryByUserId(userId);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "查询成功");
            map.put("data", resultList);
        }else {
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "未登录");
        }
        return map;
    }

    @Override
    public void createPlayHistory(Integer userId, Integer songId) {
        //根据userId和songId查询之前是否播放过
        String code = this.getAPlayHistory(userId, songId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        if(code.equals("1")){ //不存在（没有播放过），插入记录
            PlayHistory playHistory = new PlayHistory();
            playHistory.setUserId(userId);
            playHistory.setSongId(songId);
            playHistory.setPlayTime(currentTime);
            playHistoryMapper.insert(playHistory);
        }else if(code.equals("2")){ //存在（播放过），更新时间
            PlayHistory playHistory = new PlayHistory();
            playHistory.setPlayTime(currentTime);
            //基于userId和songId修改
            UpdateWrapper<PlayHistory> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",userId);
            updateWrapper.eq("song_id", songId);
            playHistoryMapper.update(playHistory, updateWrapper);
        }else {
            return;
        }
    }

    /**
     * 根据userId和songId查询之前是否播放过
     */
    public String getAPlayHistory(Integer userId, Integer songId){
        if(userId != null && songId != null){
            PlayHistory playHistory = playHistoryMapper.selectOne(new QueryWrapper<PlayHistory>().eq("user_id", userId)
                    .eq("song_id", songId));
            if(playHistory != null){
                return "2"; //存在
            }else{
                return "1"; //不存在
            }
        }else{
            return "参数错误";
        }
    }

}
