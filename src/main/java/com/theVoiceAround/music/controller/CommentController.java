package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.Comment;
import com.theVoiceAround.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 14:54
 * @description 歌曲或歌单的评论Controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @PostMapping("/addComment")
    public Map addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    /**
     * 根据歌曲id查询当前歌曲的评论列表
     */
    @GetMapping("/commentOfSongId")
    public Map commentOfSongId(Integer songId){
        return commentService.getCommentOfSongId(songId);
    }

    /**
     * 根据歌单id查询当前歌单的评论列表
     */
    @GetMapping("/commentOfSongListId")
    public Map commentOfSongListId(Integer songListId){
        return commentService.getCommentOfSongListId(songListId);
    }

    /**
     * 根据歌单id查询当前歌单的评论列表，带别名及其他参数
     */
    @GetMapping("/getCommentBySongListId")
    public Map getCommentBySongListId(Integer songListId){
        return commentService.getCommentBySongListId(songListId);
    }

    /**
     * 根据评论id删除一条评论
     */
    @GetMapping("/deleteAComment")
    public Map deleteAComment(Integer commentId){
        return commentService.deleteAComment(commentId);
    }

    /**
     * 点赞评论
     *
     */
    @GetMapping("/like")
    public Map like(Integer commentId, Integer userId){
        return commentService.like(commentId, userId);
    }
}
