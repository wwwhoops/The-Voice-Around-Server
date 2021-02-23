package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/2/2 15:03
 * @description 歌手实体类
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", pic='" + pic + '\'' +
                ", birth=" + birth +
                ", location='" + location + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
