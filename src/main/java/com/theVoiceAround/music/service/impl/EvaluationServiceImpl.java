package com.theVoiceAround.music.service.impl;

import com.theVoiceAround.music.entity.Evaluation;
import com.theVoiceAround.music.mapper.EvaluationMapper;
import com.theVoiceAround.music.service.EvaluationService;
import com.theVoiceAround.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 10:10
 * @description 评价Service的实现类
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public Map addEvaluation(Evaluation evaluation) {
        Map map = new HashMap();
        try{
            if (evaluation.getSongListId() != null
                    && evaluation.getConsumerId() != null){
                evaluationMapper.insert(evaluation);
                map.put(Consts.CODE, "1");
                map.put(Consts.MESSAGE, "评价成功");
            }else {
                map.put(Consts.CODE, "0");
                map.put(Consts.MESSAGE, "参数id错误");
            }
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(EvaluationServiceImpl.class);
            logger.error("唯一索引约束：" + e.getMessage());
            map.put(Consts.CODE, "0");
            map.put(Consts.MESSAGE, "重复评分");
        }
        return map;
    }

    @Override
    public double averageScore(Integer songListId) {
        //查询总得分
        double sumScore = evaluationMapper.getSumSocre(songListId); //定义为double型，否则计算出的值不为double
        //查询评分人数
        double totalNumOfPeople = evaluationMapper.getTotalNumOfPeople(songListId);
        if(totalNumOfPeople == 0){
            return 5.0;
        }
        double averageScore = sumScore/totalNumOfPeople;
        //四舍五入保留一位小数
        averageScore = (double) Math.round(averageScore * 10) / 10;
        return averageScore;
    }
}
