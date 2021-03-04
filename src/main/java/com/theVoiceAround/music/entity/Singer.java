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
 * @date 2021/2/2 15:03
 * @description 歌手实体类
 */
@Data
@TableName("singer")
public class Singer implements Serializable{

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private Byte sex;

    /**
     * 头像
     */
    @TableField("pic")
    private String pic;

    /**
     * 生日
     */
    @TableField("birth")
    private String birth;

    /**
     * 所在地区
     */
    @TableField("location")
    private String location;

    /**
     * 歌手简介
     */
    @TableField("introduction")
    private String introduction;
}
