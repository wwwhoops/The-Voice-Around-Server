package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theVoiceAround.music.entity.SongList;
import com.theVoiceAround.music.mapper.SongListMapper;
import com.theVoiceAround.music.service.SongListService;
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
 * @date 2021/3/3 10:19
 * @description 歌单Service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public Map addSongList(SongList songList) {
        Map map = new HashMap<>();
        if(songList != null && !songList.getTitle().equals("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = dateFormat.format(new Date());
            songList.setCreateTime(currentTime);
            songListMapper.insert(songList);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "添加成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "添加失败, 歌单标题不能为空");
        }
        return map;
    }

    @Override
    public Map getAllSongListPage(int pageSize, int pageNum, String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        if(title != null && !title.equals("")){
            queryWrapper.like("title", title);
        }
        Map map = new HashMap<>();
        IPage<SongList> iPage = new Page<>(pageNum, pageSize);
        IPage<SongList> songListIPage = songListMapper.selectPage(iPage, queryWrapper);
        long total = songListIPage.getTotal();
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", songListIPage);
        map.put("total", total);
        return map;
    }

    @Override
    public SongList selectSongListById(Integer id) {
        return songListMapper.selectById(id);
    }

    @Override
    public Map deleteSongList(Integer id) {
        Map map = new HashMap<>();
        if(id != null && id != 0){
            songListMapper.deleteById(id);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "删除失败, 歌单ID错误");
        }
        return map;
    }

    @Override
    public Map updateSongList(SongList songList) {
        //基于主键修改
        Map map = new HashMap<>();
        if(songList != null){
            //执行更新
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = dateFormat.format(new Date());
            songList.setUpdateTime(currentTime);
            songListMapper.updateById(songList);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "修改成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "修改失败, 歌曲为空");
        }
        return map;
    }

    @Override
    public Map selectAllSongList() {
        Map map = new HashMap();
        List<SongList> resultList = songListMapper.selectList(null);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", resultList);
        map.put("total", resultList.size());
        return map;
    }
}
