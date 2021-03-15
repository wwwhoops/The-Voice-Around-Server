package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theVoiceAround.music.entity.Comment;
import com.theVoiceAround.music.mapper.CommentMapper;
import com.theVoiceAround.music.service.CommentService;
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
 * @date 2021/3/15 14:56
 * @description 歌曲或歌单评论Service的实现类
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map addComment(Comment comment) {
        Map map = new HashMap();
        if(comment.getConsumerId() != null && comment.getType() != null
                && !comment.getContent().equals("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = dateFormat.format(new Date());
            comment.setCreateTime(currentTime);
            commentMapper.insert(comment);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "评论成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "参数错误");
        }
        return map;
    }

    @Override
    public Map getCommentOfSongId(Integer songId) {
        Map map = new HashMap();
        if(songId != null){
            List resultList = commentMapper.selectList(new QueryWrapper<Comment>().eq("song_id", songId));
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "查询成功");
            map.put("data", resultList);
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "歌曲ID为空");
        }
        return map;
    }

    @Override
    public Map getCommentOfSongListId(Integer songListId) {
        Map map = new HashMap();
        if(songListId != null){
            List resultList = commentMapper.selectList(new QueryWrapper<Comment>().eq("song_list_id", songListId));
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "查询成功");
            map.put("data", resultList);
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "歌单ID为空");
        }
        return map;
    }
}
