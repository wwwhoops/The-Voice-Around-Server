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
 * @date 2021/3/3 16:07
 * @description 歌单里面包含的歌曲实体类
 */
@Data
@TableName("list_song")
public class ListSong implements Serializable{
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

}
