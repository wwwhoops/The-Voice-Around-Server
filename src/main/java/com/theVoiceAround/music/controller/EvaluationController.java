package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.Evaluation;
import com.theVoiceAround.music.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/15 10:08
 * @description 歌单评价Controller
 */
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * 新增评价
     */
    @PostMapping("/addEvaluation")
    public Map addEvaluation(@RequestBody Evaluation evaluation){
        return evaluationService.addEvaluation(evaluation);
    }

    /**
     * 获取歌单的平均分
     */
    @GetMapping("/averageScore")
    public double averageScore(Integer songListId){
        return evaluationService.averageScore(songListId);
    }

}
