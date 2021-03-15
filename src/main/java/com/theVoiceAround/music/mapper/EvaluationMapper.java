package com.theVoiceAround.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.theVoiceAround.music.entity.Evaluation;

/**
 * @author Taliy4h
 * @date 2021/3/15 10:11
 * @description 评价Mapper
 */
public interface EvaluationMapper extends BaseMapper<Evaluation> {

    int getSumSocre (Integer songListId);

    int getTotalNumOfPeople(Integer songListId);
}
