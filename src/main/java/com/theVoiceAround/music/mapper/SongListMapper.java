package com.theVoiceAround.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.theVoiceAround.music.entity.SongList;

import java.util.List;

/**
 * @author Taliy4h
 * @date 2021/3/3 10:18
 * @description 歌单Mapper
 */
public interface SongListMapper extends BaseMapper<SongList>{
    List getAllSongListByKeywords(String keywords);
}
