package com.theVoiceAround.music.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.theVoiceAround.music.entity.Singer;
import com.theVoiceAround.music.service.SingerService;
import com.theVoiceAround.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/2/3 15:05
 * @description 歌手Controller
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public Map addSinger(@RequestBody Singer singer){
        Map map = singerService.addSinger(singer);
        return map;
    }

    /**
     * 分页查询所有歌手
     * params: 分页参数以及查询条件
     */
    @GetMapping("/allSingerPage")
    public Map selectAllSingerPage(int pageSize, int pageNum, String singerName){
        Map resultMap = singerService.selectAllSingerPage(pageSize, pageNum, singerName);
        return resultMap;
    }

    /**
     * 搜索框按姓名模糊搜索所有歌手
     */
    @GetMapping("/getSingerByName")
    public Map getSingerByName(String name){
        Map resultMap = singerService.getSingerByName(name);
        return resultMap;
    }


    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Map updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id){
        Map map = new HashMap();
        if(avatorFile.isEmpty()){
            map.put(Consts.CODE, 0);
            map.put(Consts.MESSAGE, "上传文件失败");
            return map;
        }
        //文件名等于当前时间 + 源文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        File file1 = new File(filePath);
        //如果文件路径不存在，则新增
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件路径：相对路径
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            map = singerService.updateSinger(singer);
            map.put(Consts.MESSAGE, "上传成功");
            map.put("pic",storeAvatorPath);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put(Consts.MESSAGE, "上传失败"+e.getMessage());
        } finally {
            return map;
        }
    }

}
