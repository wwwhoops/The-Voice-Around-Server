package com.theVoiceAround.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.theVoiceAround.music.entity.Singer;

import java.util.List;

/**
 * @author Taliy4h
 * @date 2021/2/2 16:10
 * @description 歌手Mapper
 */
public interface SingerMapper extends BaseMapper<Singer>{
    List getSingerByPicName(String picName);

}
