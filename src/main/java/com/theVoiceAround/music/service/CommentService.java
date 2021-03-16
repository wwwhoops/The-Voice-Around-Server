package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.Comment;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 14:55
 * @description 歌曲或歌单评论Service
 */
public interface CommentService {

    Map addComment(Comment comment);

    Map getCommentOfSongId(Integer songId);

    Map getCommentOfSongListId(Integer songListId);

    Map getCommentBySongListId(Integer songListId);

    Map deleteAComment(Integer commentId);
}
