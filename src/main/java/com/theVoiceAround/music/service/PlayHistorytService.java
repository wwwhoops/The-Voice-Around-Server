package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.Collect;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 23:09
 * @description 播放记录Service
 */
public interface PlayHistorytService {

    Map getHistoryByUserId(Integer userId);

    void createPlayHistory(Integer userId, Integer songId);
}
