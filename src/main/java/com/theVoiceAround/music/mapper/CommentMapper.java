package com.theVoiceAround.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.theVoiceAround.music.entity.Comment;

import java.util.List;

/**
 * @author Taliy4h
 * @date 2021/3/15 14:56
 * @description 歌曲或歌单评论Mapper
 */
public interface CommentMapper extends BaseMapper<Comment>{
    List getCommentBySongListId(Integer songListId);
}
