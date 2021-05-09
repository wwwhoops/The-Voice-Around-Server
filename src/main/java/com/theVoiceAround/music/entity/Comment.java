package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/3/15 14:39
 * @description
 */
@Data
@TableName("comment")
public class Comment implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("consumer_id")
    private Integer consumerId;

    /**
     * type 收藏类型，0歌曲1歌单
     */
    @TableField("type")
    private Byte type;

    /**
     * 歌曲id
     */
    @TableField("song_id")
    private Integer songId;

    /**
     * 歌单id
     */
    @TableField("song_list_id")
    private Integer songListId;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 获得点赞数
     */
    @TableField("up")
    private Integer up;

    /**
     * 用户头像
     */
    @TableField(exist = false)  //不是数据库字段,但必须使用
    private String avatar;

    /**
     * 用户名
     */
    @TableField(exist = false)  //不是数据库字段,但必须使用
    private String username;

}
