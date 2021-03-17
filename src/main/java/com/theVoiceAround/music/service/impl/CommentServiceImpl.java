package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theVoiceAround.music.entity.Comment;
import com.theVoiceAround.music.entity.LikeRecords;
import com.theVoiceAround.music.mapper.CommentMapper;
import com.theVoiceAround.music.mapper.LikeRecordsMapper;
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

    @Autowired
    private LikeRecordsMapper likeRecordsMapper;

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

    @Override
    public Map getCommentBySongListId(Integer songListId) {
        Map map = new HashMap();
        if(songListId != null){
            List resultList = commentMapper.getCommentBySongListId(songListId);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "查询成功");
            map.put("data", resultList);
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "歌单ID为空");
        }
        return map;
    }

    @Override
    public Map deleteAComment(Integer commentId) {
        Map map = new HashMap();
        if(commentId != null){
            commentMapper.deleteById(commentId);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "参数错误");
        }
        return map;
    }

    @Override
    public Map like(Integer commentId, Integer userId) {
        Map map = new HashMap();
        if(commentId != null && userId != null){
            //查询是否已经点过赞
            LikeRecords likeRecords = likeRecordsMapper.selectOne(
                    new QueryWrapper<LikeRecords>().eq("consumer_id", userId)
            .eq("comment_id", commentId));
            if(likeRecords != null){
                map.put(Consts.CODE, "0");
                map.put(Consts.MESSAGE, "已经点过赞了");
            }else{
                //查询当前评论对象
                Comment comment = commentMapper.selectById(commentId);
                //获取当前评论的点赞数
                int currentUps = comment.getUp();
                //执行点赞
                comment.setUp(currentUps + 1);
                commentMapper.updateById(comment);
                //向点赞记录表中插入数据
                LikeRecords likeRecords1 = new LikeRecords();
                likeRecords1.setCommentId(commentId);
                likeRecords1.setConsumerId(userId);
                likeRecordsMapper.insert(likeRecords1);
                map.put(Consts.CODE, "1");
                map.put(Consts.MESSAGE, "点赞成功");
            }
        }
        return map;
    }
}
