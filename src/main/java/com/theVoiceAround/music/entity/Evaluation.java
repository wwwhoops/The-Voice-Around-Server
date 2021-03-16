package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/3/15 9:58
 * @description 评价实体类
 */
@Data
@TableName("evaluation")
public class Evaluation implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌单id
     */
    @TableField("song_list_id")
    private Integer songListId;

    /**
     * 用户id
     */
    @TableField("consumer_id")
    private Integer consumerId;

    /**
     * 评分
     */
    @TableField("score")
    private Integer score;


}
