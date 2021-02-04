package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theVoiceAround.music.entity.Singer;
import com.theVoiceAround.music.mapper.SingerMapper;
import com.theVoiceAround.music.service.SingerService;
import com.theVoiceAround.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/2/3 14:34
 * @description
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public Map addSinger(Singer singer) {
        Map map = new HashMap<>();
        if(singer != null){
            singerMapper.insert(singer);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "添加成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "添加失败");
        }

        return map;
    }

    @Override
    public Map updateSinger(Singer singer) {
        //基于主键修改
        Map map = new HashMap<>();
        if(singer != null){
            singerMapper.updateById(singer);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "修改成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "修改失败");
        }
        return map;
    }

    @Override
    public Map deleteSinger(Integer id) {
        Map map = new HashMap<>();
        if(id != null && id != 0){
            singerMapper.deleteById(id);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "删除失败");
        }
        return map;
    }

    @Override
    public Map selectAllSinger(int pageSize, int pageNum, String name) {
        Map map = new HashMap<>();
        IPage<Singer> iPage = new Page<>(pageNum, pageSize);
        UpdateWrapper<Singer> updateWrapper = new UpdateWrapper<>();
        if(name != null && !name.equals("")){
            updateWrapper.eq("name", name);
        }else {
            updateWrapper = null;
        }
        IPage<Singer> singerIPage = singerMapper.selectPage(iPage, updateWrapper);
        long total = singerIPage.getTotal();
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", singerIPage);
        map.put("total", total);
        return map;
    }

    @Override
    public List<Singer> selectSingerById(Integer id) {
        return null;
    }

    @Override
    public List<Singer> selectSingerByName(String name) {
        return null;
    }

    @Override
    public List<Singer> selectSingerBySex(Integer sex) {
        return null;
    }
}
