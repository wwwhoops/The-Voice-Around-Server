package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/3/3 9:59
 * @description 歌单实体类
 */
@Data
@TableName("song_list")
public class SongList implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌单标题
     */
    @TableField("title")
    private String title;

    /**
     * 歌单图片(路径)
     */
    @TableField("pic")
    private String pic;

    /**
     * 歌单简介
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 歌单风格
     */
    @TableField("style")
    private String style;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 星级
     */
    @TableField("star_level")
    private String starLevel;
}
