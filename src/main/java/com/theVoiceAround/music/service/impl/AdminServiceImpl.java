package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theVoiceAround.music.entity.Admin;
import com.theVoiceAround.music.mapper.AdminMapper;
import com.theVoiceAround.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Taliy4h
 * @date 2021/2/1 16:02
 * @description 管理员Service实现类
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * @param username 用户名
     * @param password 密码
     * @return 用户是否存在
     */
    @Override
    public List<Admin> verifyPassword(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>(); //泛型推断，前面写了后面可不写
        /**
         * 以下为测试mybatis-plus 常用CRUD
         */

        //eq:设置等值查询，第一个参数为数据库中的字段名
//        queryWrapper.eq("name",username);

        //lt:设置小于查询，le: 小于等于 ; gt: 大于 ; ge: 大于等于....
//        queryWrapper.lt("age",20);

        //模糊查询，设置数据库中字段名为name的列包含张的查询 like:%?%, likeLeft:%?, likeRight:?%
//        queryWrapper.like("name","张");

        //添加，插入数据
//        Admin admin = new Admin();
//        admin.setName("taliyah");
//        admin.setPassword("123");
//        adminMapper.insert(admin);//这里调用的是adminMapper继承于BaseMapper中的insert方法

        //基于主键id进行修改更新
//        Admin admin = adminMapper.selectById(2);//查询id为2的对象
//        admin.setPassword("999");//改变该对象的一个值
//        adminMapper.updateById(admin);//传入该对象进行更新

        //基于条件批量修改 : 修改age为23岁的人的name为heywmn
//        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
//        Admin admin = new Admin();
//        admin.setName("heywmn");
//        updateWrapper.eq("age",20);
//        adminMapper.update(admin,updateWrapper);//执行修改

        //基于id删除一个
//        adminMapper.deleteById(3);

        //基于条件进行批量删除 : 删除年龄为23的数据
//        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
//        queryWrapper.eq("age",23);
//        adminMapper.delete(updateWrapper);

        //分页查询:
        // Ipage:参数1：当前页，默认1； 参数2：每页显示条数，默认10。目前分页查询仅仅支持单表查询，不能在表连接时使用
//        IPage<Admin> page = new Page<>(1,2);
        //selectPage: 参数1：分页参数； 参数2：查询条件，为空时分页查询所有
//        updateWrapper.eq("password", "123");//密码为123
//        IPage<Admin> adminIPage = adminMapper.selectPage(page, updateWrapper);
//        long total = adminIPage.getTotal();
//        System.out.println("总记录数： " + total);
//        adminIPage.getRecords().forEach(admin -> System.out.println("admin ===> " + admin));


        queryWrapper.eq("name", username).eq("password",password); //两次.eq类似于and
//        return adminMapper.verifyPassword(username, password) > 0; //使用传统mybatis
        return adminMapper.selectList(queryWrapper); //使用mybatis-plus
    }

}
