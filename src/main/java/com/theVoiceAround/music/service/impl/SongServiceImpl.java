package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theVoiceAround.music.entity.Singer;
import com.theVoiceAround.music.entity.Song;
import com.theVoiceAround.music.mapper.SongMapper;
import com.theVoiceAround.music.service.SongService;
import com.theVoiceAround.music.utils.Consts;
import com.theVoiceAround.music.utils.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/2/22 17:46
 * @description 歌曲Service实现类
 */
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    @Override
    public Map addSong(Song song) {
        Map map = new HashMap<>();
        if(song != null && !song.getName().equals("")){
            //执行添加
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = dateFormat.format(new Date());
            song.setCreateTime(currentTime);
            songMapper.insert(song);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "添加成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "添加失败, 歌曲名不能为空");
        }
        return map;
    }

    @Override
    public Map selectSingerSongPage(int pageSize, int pageNum, String songName, int singerId) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id", singerId);
        if(songName != null && !songName.equals("")){
            queryWrapper.like("name", songName);
        }
        Map map = new HashMap<>();
        IPage<Song> iPage = new Page<>(pageNum, pageSize);
        IPage<Song> songIPage = songMapper.selectPage(iPage, queryWrapper);
        long total = songIPage.getTotal();
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", songIPage);
        map.put("total", total);
        return map;
    }

    @Override
    public Map updateSong(Song song) {
        //基于主键修改
        Map map = new HashMap<>();
        if(song != null){
            //执行更新
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = dateFormat.format(new Date());
            song.setUpdateTime(currentTime);
            songMapper.updateById(song);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "修改成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "修改失败, 歌曲为空");
        }
        return map;
    }

    @Override
    public Map deleteSong(Integer id) {
        Map map = new HashMap<>();
        if(id != null && id != 0){
            songMapper.deleteById(id);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "删除失败, 歌手ID错误");
        }
        return map;
    }

    @Override
    public Song selectSongById(Integer id) { return songMapper.selectById(id); }
}