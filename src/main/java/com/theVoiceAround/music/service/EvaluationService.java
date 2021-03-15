package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.Evaluation;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 10:09
 * @description 评价Service
 */
public interface EvaluationService {

    Map addEvaluation(Evaluation evaluation);

    double averageScore(Integer songListId);
}
