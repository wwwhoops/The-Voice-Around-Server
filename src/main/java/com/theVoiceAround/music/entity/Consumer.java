package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/3/5 9:14
 * @description 客户端用户实体类
 */
@Data
@TableName("Consumer")
public class Consumer implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 电话号码
     */
    @TableField("phone_num")
    private String phoneNum;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 生日
     */
    @TableField("birth")
    private String birth;

    /**
     * 签名
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 地区
     */
    @TableField("location")
    private String location;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

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
}
