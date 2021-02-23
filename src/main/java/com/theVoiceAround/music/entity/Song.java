package com.theVoiceAround.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Taliy4h
 * @date 2021/2/22 17:30
 * @description 歌曲实体类
 */

@TableName("song")
public class Song implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手id
     */
    @TableField("singer_id")
    private Integer singerId;

    /**
     * 歌曲名称
     */
    @TableField("name")
    private String name;

    /**
     * 歌曲简介
     */
    @TableField("introduction")
    private String introduction;

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
     * 歌曲封面图片
     */
    @TableField("pic")
    private String pic;

    /**
     * 歌词
     */
    @TableField("lyric")
    private String lyric;

    /**
     * 歌曲地址
     */
    @TableField("url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singerId=" + singerId +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", pic='" + pic + '\'' +
                ", lyric='" + lyric + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
