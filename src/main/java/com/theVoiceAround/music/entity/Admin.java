package com.theVoiceAround.music.entity;

/**
 * @author Taliy4h
 * @date 2021/2/1 15:19
 * @description 管理员实体类，为了前后台的传输，需要实现序列化：继承Serializable
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@TableName("admin")//该注解标明数据库中的表名和实体类对应，默认将类名识别成表名
public class Admin implements Serializable{

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)//该注解标明数据库中表的主键和实体类中的属性对应，如果两者本身相同，可省略不写，type用于指定数据库中主键的类型，此处为自增
    private Integer id;

    /**
     * 用户名
     */
    @TableField("name")//该字段用于解决数据库表中字段和实体类属性的映射,此外还有一个常用属性：exist=false,表示该属性不映射数据库中的字段
    private String name;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

}
