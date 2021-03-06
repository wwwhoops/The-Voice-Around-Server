package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.Consumer;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/5 10:49
 * @description 客户端用户Service
 */
public interface ConsumerService {
    Map selectAllUserPage(int pageSize, int pageNum, String username);

    Map addConsumer(Consumer consumer);

    Consumer selectUserById(Integer id);

    Map deleteUser(Integer id);

    Map updateConsumer(Consumer consumer);

    Map selectAllConsumer();
}
