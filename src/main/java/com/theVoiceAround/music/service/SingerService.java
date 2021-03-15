package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.Singer;

import java.util.List;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/2/3 14:33
 * @description 歌手Service
 */
public interface SingerService {
    /**
     * 添加歌手
     */
    Map addSinger(Singer singer);

    /**
     * 修改歌手信息
     */
    Map updateSinger(Singer singer);

    /**
     * 根据id删除歌手
     */
    Map deleteSinger(Integer id);

    /**
     * 分页查询所有歌手，包含根据姓名模糊查询
     */
    Map selectAllSingerPage(int pageSize, int pageNum, String singerName);

    /**
     * 不分页查询所有歌手
     */
    Map selectAllSinger();

    /**
     * 根据id查询歌手
     */
    Singer selectSingerById(Integer id);


    /**
     * 根据性别查询歌手信息
     */
    Map selectSingerBySex(Integer sex);

}
