package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theVoiceAround.music.entity.Consumer;
import com.theVoiceAround.music.mapper.ConsumerMapper;
import com.theVoiceAround.music.service.ConsumerService;
import com.theVoiceAround.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/5 10:49
 * @description 客户端用户Service实现类
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public Map selectAllUserPage(int pageSize, int pageNum, String username) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        if(username != null && !username.equals("")){
            queryWrapper.like("username", username);
        }
        Map map = new HashMap<>();
        IPage<Consumer> iPage = new Page<>(pageNum, pageSize);
        IPage<Consumer> userIPage = consumerMapper.selectPage(iPage, queryWrapper);
        long total = userIPage.getTotal();
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", userIPage);
        map.put("total", total);
        return map;
    }

    @Override
    public Map addConsumer(Consumer consumer) {
        Map map = new HashMap<>();
        if(consumer != null && !consumer.getUsername().equals("")){
            if(consumer.getSex() == 1){
                consumer.setAvatar(Consts.DEFAULT_MALE_AVATAR_PATH);
            }else{
                consumer.setAvatar(Consts.DEFAULT_FEMALE_AVATAR_PATH);
            }
            try {
//                String currentTime = GetDateOnline.getDateOnline();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentTime = dateFormat.format(new Date());
                consumer.setCreateTime(currentTime);
            } catch (Exception e) {
                e.printStackTrace();
                Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);
                logger.error("获取时间出错：" + e.getMessage());
            }
            consumerMapper.insert(consumer);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "添加成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "添加失败, 用户名不能为空");
        }
        return map;
    }

    @Override
    public Consumer selectUserById(Integer id) {
        return consumerMapper.selectById(id);
    }

    @Override
    public Map deleteUser(Integer id) {
        Map map = new HashMap<>();
        if(id != null && id != 0){
            consumerMapper.deleteById(id);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "删除成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "删除失败, 用户ID错误");
        }
        return map;
    }

    @Override
    public Map updateConsumer(Consumer consumer) {
        //基于主键修改
        Map map = new HashMap<>();
        if(consumer != null){
            consumerMapper.updateById(consumer);
            map.put(Consts.CODE, "1");
            map.put(Consts.MESSAGE, "修改成功");
        }else{
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "修改失败, 用户为空");
        }
        return map;
    }

    @Override
    public Map selectAllConsumer() {
        Map map = new HashMap();
        List<Consumer> resultList = consumerMapper.selectList(null);
        map.put(Consts.CODE, "1");
        map.put(Consts.MESSAGE, "查询成功");
        map.put("data", resultList);
        map.put("total", resultList.size());
        return map;
    }
}
