package com.theVoiceAround.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.theVoiceAround.music.entity.ListSong;

import java.util.List;

/**
 * @author Taliy4h
 * @date 2021/3/3 16:19
 * @description 歌单里面歌曲Mapper
 */
public interface ListSongMapper extends BaseMapper<ListSong>{

    List getListSong(Integer sogListId);
}
