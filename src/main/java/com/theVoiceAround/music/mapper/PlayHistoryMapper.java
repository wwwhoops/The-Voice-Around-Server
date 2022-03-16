package com.theVoiceAround.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.theVoiceAround.music.entity.Collect;
import com.theVoiceAround.music.entity.PlayHistory;

import java.util.List;

/**
 * @author Taliy4h
 * @date 2021/3/15 23:11
 * @description 收藏Mapper
 */
public interface PlayHistoryMapper extends BaseMapper<PlayHistory> {
    List getHistoryByUserId(Integer userId);
}
