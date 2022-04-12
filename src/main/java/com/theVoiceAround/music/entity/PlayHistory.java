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
 * @description 播放记录实体类
 */
@Data
@TableName("play_history")
public class PlayHistory implements Serializable {

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
     * 歌曲id
     */
    @TableField("song_id")
    private Integer songId;

    /**
     * 播放时间
     */
    @TableField("play_time")
    private String playTime;

    /**
     * 歌手名
     */
    @TableField(exist = false)
    private String singerName;

    /**
     * 专辑
     */
    @TableField(exist = false)
    private String songAlbum;

    /**
     * 歌词
     */
    @TableField(exist = false)
    private String songLyric;

    /**
     * 歌曲名
     */
    @TableField(exist = false)
    private String songName;

    /**
     * 歌曲图片
     */
    @TableField(exist = false)
    private String songPic;

    /**
     * 歌曲地址
     */
    @TableField(exist = false)
    private String songUrl;
}
