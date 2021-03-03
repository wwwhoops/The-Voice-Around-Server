package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.SongList;
import com.theVoiceAround.music.service.SongListService;
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
 * @date 2021/3/3 10:17
 * @description 歌单Controller
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 创建歌单
     */
    @PostMapping("/addSongList")
    public Map addSongList(@RequestBody SongList songList){
        Map map = songListService.addSongList(songList);
        return map;
    }

    /**
     * 分页查询所有歌单, 包含模糊搜索
     * params: 分页参数以及查询条件
     */
    @GetMapping("/getAllSongList")
    public Map getAllSongListPage(int pageSize, int pageNum, String title){
        Map resultMap = songListService.getAllSongListPage(pageSize, pageNum, title);
        return resultMap;
    }

    /**
     * 删除一个歌手
     */
    @GetMapping("/deleteASongList")
    public Map deleteASongList(int id){
        //删除前查询出旧文件路径(歌单封面图)
        SongList songList = songListService.selectSongListById(id);
        String oldFilePath = songList.getPic();
        Map map = songListService.deleteSongList(id);
        //删除歌手后将其歌手图片删除
        if(songList != null && !oldFilePath.equals(Consts.DEFAULT_SONG_LIST_PIC_PATH)){
            File oldFile = new File(Consts.FILE_PATH + oldFilePath);
            oldFile.delete();
        }
        return map;
    }

    /**
     * 更新歌单封面图
     */
    @PostMapping("/updateSongListPic")
    public Map updateSingerPic(@RequestParam("file") MultipartFile albumPic, @RequestParam("id") int id){
        Map map = new HashMap();
        if(albumPic.isEmpty()){ //前端做了类型判断，此处不再判断
            map.put(Consts.CODE, 0);
            map.put(Consts.MESSAGE, "文件为空");
            return map;
        }
        //文件名等于当前时间 + 源文件名
        String fileName = System.currentTimeMillis() + albumPic.getOriginalFilename();
        String filePath = Consts.FILE_PATH + Consts.SONG_LIST_PIC_PATH;
        File file1 = new File(filePath);
        //如果文件路径不存在，则新增
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件路径：相对路径
        String storeDBPath = Consts.SONG_LIST_PIC_PATH + "/" + fileName;
        try {
            //更新前查询出旧文件路径
            SongList songList1 = songListService.selectSongListById(id);
            String oldFilePath = songList1.getPic();
            albumPic.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeDBPath);
            map = songListService.updateSongList(songList);
            //更新成功后删除旧文件，如果是默认文件则不删除
            if(songList1 != null && !oldFilePath.equals(Consts.DEFAULT_SONG_LIST_PIC_PATH)){
                File oldFile = new File(Consts.FILE_PATH + oldFilePath);
                oldFile.delete();
            }
            map.put(Consts.MESSAGE, "上传成功");
            map.put("pic",storeDBPath);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put(Consts.MESSAGE, "上传失败"+e.getMessage());
        } finally {
            return map;
        }
    }

    /**
     * 修改歌单信息
     */
    @PostMapping("/updateSongList")
    public Map updateSingerInfo(@RequestBody SongList songList){
        Map map = songListService.updateSongList(songList);
        return map;
    }

}
