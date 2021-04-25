package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.Singer;
import com.theVoiceAround.music.service.SingerService;
import com.theVoiceAround.music.service.SongService;
import com.theVoiceAround.music.service.impl.ListSongServiceImpl;
import com.theVoiceAround.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private SongService songService;

    @Autowired
    private ListSongServiceImpl listSongServiceImpl;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public Map addSinger(@RequestBody Singer singer){
        Map map = singerService.addSinger(singer);
        return map;
    }

    /**
     * 根据图片名称模糊查询歌手信息
     */
    @GetMapping("/getSingerByPicName")
    public Map getSingerByPicName(String picName){
        Map resultMap = singerService.getSingerByPicName(picName);
        return resultMap;
    }

    /**
     * 分页查询所有歌手, 包含模糊搜索
     * params: 分页参数以及查询条件
     */
    @GetMapping("/allSingerPage")
    public Map selectAllSingerPage(int pageSize, int pageNum, String singerName){
        Map resultMap = singerService.selectAllSingerPage(pageSize, pageNum, singerName);
        return resultMap;
    }

    /**
     * 不分页查询所有歌手
     */
    @GetMapping("/allSinger")
    public Map selectAllSinger(){
        Map resultMap = singerService.selectAllSinger();
        return resultMap;
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Map updateSingerPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") int id){
        Map map = new HashMap();
        if(avatarFile.isEmpty()){ //前端做了类型判断，此处不再判断
            map.put(Consts.CODE, 0);
            map.put(Consts.MESSAGE, "文件为空");
            return map;
        }
        //文件名等于当前时间 + 源文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        //去除字符中的所有空白字符，这样是为了防止上传文件进入服务器因为由空格而访问不到对应资源
        fileName.replaceAll("\\s*", "");
        String filePath = Consts.FILE_PATH + Consts.SINGER_PIC_PATH;
        File file1 = new File(filePath);
        //如果文件路径不存在，则新增
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件路径：相对路径
        String storeDBPath = Consts.SINGER_PIC_PATH + "/" + fileName;
        try {
            //更新前查询出旧文件路径
            Singer singer1 = singerService.selectSingerById(id);
            String oldFilePath = singer1.getPic();
            avatarFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeDBPath);
            map = singerService.updateSinger(singer);
            //更新成功后删除旧文件
            if(singer1 != null && !oldFilePath.equals(Consts.DEFAULT_SINGER_PIC_PATH)){
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
     * 修改歌手信息
     */
    @PostMapping("/updateSingerInfo")
    public Map updateSingerInfo(@RequestBody Singer singer){
        Map map = singerService.updateSinger(singer);
        return map;
    }

    /**
     * 删除一名歌手
     */
    @GetMapping("/deleteASinger")
    public Map deleteASinger(int id){
        //删除前查询出旧文件路径
        Singer singer1 = singerService.selectSingerById(id);
        String oldFilePath = singer1.getPic();
        Map map = singerService.deleteSinger(id);
        //删除歌手后将其歌手图片删除
        if(singer1 != null && !oldFilePath.equals(Consts.DEFAULT_SINGER_PIC_PATH)){
            File oldFile = new File(Consts.FILE_PATH + oldFilePath);
            oldFile.delete();
        }
        //同时删除该歌手所拥有的歌曲，并且返回这些歌曲的id用作删除歌单中歌曲
        List songIdList = songService.deleteSongBySingerId(id);
        //根据上面得到的歌曲id，删除歌单里面拥有该歌手歌曲的数据
        for(int i=0; i<songIdList.size(); i++){
            listSongServiceImpl.deleteASongFromSongList((Integer) songIdList.get(i));
        }
        return map;
    }

    /**
     * 根据歌手性别（男、女、组合、不明）
     */
    @GetMapping("/singerBySex")
    public Map singerBySex(Integer sex){
        return singerService.selectSingerBySex(sex);
    }

}
