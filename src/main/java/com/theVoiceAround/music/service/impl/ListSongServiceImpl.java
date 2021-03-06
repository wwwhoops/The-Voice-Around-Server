package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theVoiceAround.music.entity.ListSong;
import com.theVoiceAround.music.mapper.ListSongMapper;
import com.theVoiceAround.music.service.ListSongService;
import com.theVoiceAround.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/3 16:20
 * @description 歌单歌曲Service实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public Map getListSong(Integer songListId) {
        Map map = new HashMap();
        List resultList = listSongMapper.getListSong(songListId);
        map.put("data", resultList);
        if(!resultList.isEmpty()){
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "查询成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "查询结果为空");
        }
        return map;
    }

    @Override
    public Map addSongToSongList(ListSong listSong) {
        Map map = new HashMap();
        if(listSong.getSongId() !=null && !listSong.getSongId().equals("") && listSong.getSongListId() !=null){
            listSongMapper.insert(listSong);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "添加成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "歌曲不存在");
        }
        return map;
    }

    @Override
    public Map deleteASongFromSongList(Integer songListId, Integer songId) {
        Map map = new HashMap();
        if(songListId != null && !songListId.equals("") && songId != null && !songId.equals("")){
            listSongMapper.delete(new QueryWrapper<ListSong>().eq("song_list_id", songListId).eq("song_id", songId));
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "歌曲不存在");
        }
        return map;
    }

    /**
     * 重载此方法，用于删除歌手时删除掉该歌手在歌单中所拥有的记录
     * 重载：在一个类中或在一个子类中，运行方法名相同，但参数（个数，类型）、返回值类型、访问修饰符（public、protect、private、default）不同，
     *         运行声明更广的异常；
     * 重写：子类对父类方法的实现过程进行重新编写，返回值和形参都不能变，即：外壳不变，核心重写
     *          不允许声明比父类更广的异常
     * @param songId 歌曲id
     * @return
     */
    public void deleteASongFromSongList(Integer songId) {
        try {
            listSongMapper.delete(new QueryWrapper<ListSong>().eq("song_id", songId));
        }catch (Exception e){
            e.getMessage();
            Logger logger = LoggerFactory.getLogger(ListSongServiceImpl.class);
            logger.error("删除出错：" + e.getMessage());
        }
    }
}
