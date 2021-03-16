package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theVoiceAround.music.entity.Collect;
import com.theVoiceAround.music.mapper.CollectMapper;
import com.theVoiceAround.music.service.CollectService;
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
 * @date 2021/3/15 23:10
 * @description 收藏Service实现类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Map addCollect(Collect collect) {
        Map map = new HashMap();
        //根据userId和songId查询是否已收藏
        String code = this.getACollect(collect.getUserId(), collect.getSongId());
        if(code.equals("1")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = dateFormat.format(new Date());
            collect.setCreateTime(currentTime);
            collectMapper.insert(collect);
            map.put(Consts.CODE, code);
            map.put(Consts.MESSAGE, "收藏成功");
        }else if(code.equals("2")){
            collectMapper.delete(new QueryWrapper<Collect>().eq("user_id", collect.getUserId())
                    .eq("song_id", collect.getSongId()));
            map.put(Consts.CODE, code);
            map.put(Consts.MESSAGE, "取消收藏");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, code);
        }
        return map;
    }

    /**
     * 根据userId和songId查询是否已收藏
     */
    public String getACollect(Integer userId, Integer songId){
        if(userId != null && songId != null){
            if(userId != null && songId != null){
                Collect collect = collectMapper.selectOne(new QueryWrapper<Collect>().eq("user_id", userId)
                        .eq("song_id", songId));
                if(collect != null){
                    return "2";
                }
            }else{
                return "参数错误";
            }
            return "1";
        }else {
            return "-1";
        }
    }

    @Override
    public Map getCollectByUserId(Integer userId) {
        Map map = new HashMap();
        if(userId != null){
            List resultList = collectMapper.selectList(
                    new QueryWrapper<Collect>().eq("user_id", userId));
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "查询成功");
            map.put("data", resultList);
        }else {
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "未登录");
        }
        return map;
    }

    @Override
    public Map deleteACollect(Integer userId, Integer songId) {
        Map map = new HashMap();
        if(userId != null && songId != null){
            collectMapper.delete(new QueryWrapper<Collect>().eq("user_id", userId)
                    .eq("song_id", songId));
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "参数错误");
        }
        return map;
    }
}
