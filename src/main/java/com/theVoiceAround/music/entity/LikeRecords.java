package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/3/17 11:45
 * @description 点赞记录
 */
@Data
@TableName("like_records")
public class LikeRecords implements Serializable{
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
     * 评论id
     */
    @TableField("comment_id")
    private Integer commentId;
}
