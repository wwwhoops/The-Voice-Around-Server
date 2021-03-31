package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theVoiceAround.music.entity.Song;
import com.theVoiceAround.music.mapper.SongMapper;
import com.theVoiceAround.music.service.SongService;
import com.theVoiceAround.music.utils.Consts;
import com.theVoiceAround.music.utils.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Map selectAllSongBySingerId(Integer singerId) {
        Map map = new HashMap();
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id", singerId);
        map.put("data", songMapper.selectList(queryWrapper));
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
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
        //查询歌曲文件和图片路径
        Song song1 = this.selectSongById(id);
        String fileUrl = song1.getUrl();
        String imgUrl = song1.getPic();
        if(id != null && id != 0){
            songMapper.deleteById(id);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "删除失败, 歌手ID错误");
            return map;
        }
        //删除歌曲文件
        if(song1 != null){
            File songFile = new File(Consts.FILE_PATH + fileUrl);
            songFile.delete();
        }
        //删除歌曲图片
        if(song1 != null && !imgUrl.equals(Consts.DEFAULT_SONG_PIC_PATH)){
            File imgFile = new File(Consts.FILE_PATH + imgUrl);
            imgFile.delete();
        }
        return map;
    }

    @Override
    public Song selectSongById(Integer id) { return songMapper.selectById(id); }


    /**
     * 根据歌手id删除该歌手的所有歌曲，用于当删除歌手时删除其歌曲
     */
    @Override
    public List deleteSongBySingerId(Integer id) {
        //查询出一位歌手所有的歌曲，获取每一个歌曲id
        List songIdList = new ArrayList<>();
        Map songMap = this.selectAllSongBySingerId(id);
        List<Song> dataList = (List<Song>) songMap.get("data"); //数据库查询出的所有歌曲对象
        if(!dataList.isEmpty()){
            for(int i=0; i<dataList.size(); i++){
                Map map = TypeConverter.beanToMap(dataList.get(i)); //将bean转换成Map
                songIdList.add(map.get("id")); //从Map中获取对应的值并添加到歌曲id集合中
            }
        }
        //得到歌曲id后删除歌曲
        for(int i=0; i<songIdList.size(); i++){
            this.deleteSong((Integer) songIdList.get(i));
        }
        return songIdList;
    }

    @Override
    public Map selectAllSong() {
        Map map = new HashMap();
        List<Song> resultList = songMapper.selectList(null);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", resultList);
        map.put("total", resultList.size());
        return map;
    }

    @Override
    public Map getAllSongByKeywords(String keywords) {
        Map map = new HashMap();
        List resultList = songMapper.getAllSongByKeywords(keywords);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", resultList);
        map.put("total", resultList.size());
        return map;
    }

    @Override
    public Map selectAllSongBySingerIdAlias(Integer singerId) {
        Map map = new HashMap();
        List<Song> resultList = songMapper.selectAllSongBySingerIdAlias(singerId);
        map.put("data", resultList);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        return map;
    }

    @Override
    public Map getASongBySongIdAlias(Integer songId) {
        Map map = new HashMap();
        List resultList = songMapper.getASongBySongIdAlias(songId);
        map.put("data", resultList);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        return map;
    }

    @Override
    public Map getRankList() {
        Map map = new HashMap();
        List resultList = songMapper.getRankList();
        map.put("data", resultList);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        return map;
    }

    @Override
    public Map increasePlayCount(Integer songId) {
        Map map = new HashMap();
        songMapper.increasePlayCount(songId);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "播放量+1");
        return map;
    }
}