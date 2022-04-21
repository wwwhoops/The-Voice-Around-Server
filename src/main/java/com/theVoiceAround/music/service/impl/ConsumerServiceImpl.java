package com.theVoiceAround.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theVoiceAround.music.entity.Consumer;
import com.theVoiceAround.music.mapper.ConsumerMapper;
import com.theVoiceAround.music.service.ConsumerService;
import com.theVoiceAround.music.utils.Consts;
import com.theVoiceAround.music.utils.MD5Encryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Taliy4h
 * @date 2021/3/5 10:49
 * @description 客户端用户Service实现类
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Autowired
    private PlayHistoryServiceImpl playHistorytService;

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
        //查询用户是否存在
        Consumer consumer1 = consumerMapper.selectOne(new QueryWrapper<Consumer>().eq("username", consumer.getUsername()));
        if(consumer1 != null){
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "用户名已存在");
            return map;
        }
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
//                consumer.setPassword(MD5Encryptor.EncoderBySpring(consumer.getPassword()));
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

    @Override
    public Consumer verifyPassword(String username, String password) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password",password);
        return consumerMapper.selectOne(queryWrapper);
    }

    @Override
    public String selectStyleById(Integer userId) {
        List historyList = new ArrayList();
        List styleList = new ArrayList();
        String style = "";
        //如果存在播放记录，取出播放记录并分析歌曲风格
        if(playHistorytService.getHistoryByUserId(userId).get("data") != null){
            historyList = (List) playHistorytService.getHistoryByUserId(userId).get("data");
            for(int i=0; i<historyList.size(); i++) {
                HashMap hashMap = (HashMap) historyList.get(i);
                styleList.add(hashMap.get("songStyle"));
            }
        }
        StringBuffer styleStringBuffer = new StringBuffer();
        for(int i=0; i<styleList.size(); i++){
            styleStringBuffer.append(styleList.get(i));
        }
        //去除字符中的逗号
        String styleString = styleStringBuffer.toString().replaceAll(",","");
        //获取每种风格的数量并加入到数组中进行排序（冒泡）
        int yueyu = this.getStrCount(styleString, "粤语");
        int huayu = this.getStrCount(styleString, "华语");
        int oumei = this.getStrCount(styleString, "欧美");
        int rihan = this.getStrCount(styleString, "日韩");
        int yaogun = this.getStrCount(styleString, "摇滚");
        int minyao = this.getStrCount(styleString, "民谣");
        int liuxing = this.getStrCount(styleString, "流行");
        int qita = this.getStrCount(styleString, "其他");
        int totalCount = yueyu + huayu + oumei + rihan + yaogun + minyao + liuxing + qita;  //所有类型数量和
        if(yueyu > totalCount/4){
            style = style + "粤语 ";
        }
        if(huayu > totalCount/4){
            style = style + "华语 ";
        }
        if(oumei > totalCount/4){
            style = style + "欧美 ";
        }
        if(rihan > totalCount/4){
            style = style + "日韩 ";
        }
        if(yaogun > totalCount/4){
            style = style + "摇滚 ";
        }
        if(minyao > totalCount/4){
            style = style + "民谣 ";
        }
        if(liuxing > totalCount/4){
            style = style + "流行 ";
        }
        if(qita > totalCount/4){
            style = style + "其他 ";
        }
        return style;
    }

    private int getStrCount(String mainStr, String subStr) {
        // 声明一个要返回的变量
        int count = 0;
        // 声明一个初始的下标，从初始位置开始查找
        int index = 0;
        // 获取主数据的长度
        int mainStrLength = mainStr.length();
        // 获取要查找的数据长度
        int subStrLength = subStr.length();
        // 如果要查找的数据长度大于主数据的长度则返回0
        if (subStrLength > mainStrLength) {
            return 0;
        }
        // 循环使用indexOf查找出现的下标，如果出现一次则count++
        while ((index = mainStr.indexOf(subStr, index)) != -1) {
            count++;
            // 从找到的位置下标加上要查找的字符串长度，让指针往后移动继续查找
            index += subStrLength;
        }
        return count;
    }
}
