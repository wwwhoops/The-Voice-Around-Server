package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/3/15 22:58
 * @description 收藏实体类
 */
@Data
@TableName("collect")
public class Collect implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 收藏类型，0歌曲1歌单
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
     * 收藏时间
     */
    @TableField("create_time")
    private String createTime;
}
