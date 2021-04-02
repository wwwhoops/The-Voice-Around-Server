package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.Song;
import com.theVoiceAround.music.service.SongService;
import com.theVoiceAround.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/2/22 17:45
 * @description 歌曲Controller
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     */
    @PostMapping("/add")
    public Map addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile){
        Map map = new HashMap();
        String singerId = request.getParameter("singerId").trim(); //所属歌手id
        String name = request.getParameter("name").trim();  //歌曲名称
        String album = request.getParameter("album").trim(); //歌曲所在专辑
        String pic = Consts.DEFAULT_SONG_PIC_PATH;  //歌曲默认图片
        String lyric = request.getParameter("lyric").trim(); //歌词

        //获取文件类型
        String fileAllName = mpFile.getOriginalFilename();
        String fileType = fileAllName.substring(fileAllName.lastIndexOf("."),fileAllName.length());
        //上传歌曲文件
        if(mpFile.isEmpty() || !fileType.equals(".mp3")){
            map.put(Consts.CODE,"0");
            map.put(Consts.MESSAGE,"歌曲添加失败, 上传文件为空或文件格式错误");
            return map;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();       
        //去除字符中的所有空白字符，这样是为了防止上传文件进入服务器因为由空格而访问不到对应资源
        fileName.replaceAll("\\s*", "");
        String filePath = Consts.FILE_PATH + "/music/song";
        File file1 = new File(filePath);
        //如果文件路径不存在，则新增
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件路径：相对路径
        String storeUrlPath = "/music/song/" + fileName;

        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            if(singerId != null && !singerId.equals("")){
                song.setSingerId(Integer.parseInt(singerId));
            }
            song.setName(name);
            song.setAlbum(album);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            map = songService.addSong(song);
            map.put("songPic",storeUrlPath);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put(Consts.MESSAGE, "歌曲添加失败"+e.getMessage());
        } finally {
            return map;
        }
    }

    /**
     * 根据分页查询某歌手的歌曲, 包含模糊搜索
     * params: 分页参数以及查询条件
     */
    @GetMapping("/singerSongPage")
    public Map selectSingerSongPage(int pageSize, int pageNum, String songName, int singerId){
        Map resultMap = songService.selectSingerSongPage(pageSize, pageNum, songName, singerId);
        return resultMap;
    }

    /**
     * 根据歌手id不分页查询所有歌曲
     */
    @GetMapping("/allSong")
    public Map selectAllSong(Integer singerId){
        Map resultMap = songService.selectAllSongBySingerId(singerId);
        return resultMap;
    }
    /**
     * 根据歌手id不分页查询所有歌曲，加别名
     */
    @GetMapping("/allSongAlias")
    public Map selectAllSongAlias(Integer singerId){
        Map resultMap = songService.selectAllSongBySingerIdAlias(singerId);
        return resultMap;
    }

    /**
     * 查询所有歌曲或根据歌名模糊查询所有歌曲
     */
    @GetMapping("/getAllSong")
    public Map getAllSong(){
        return songService.selectAllSong();
    }

    /**
     * 根据歌手名字或歌曲名字模糊搜索歌曲或歌手的歌曲
     */
    @GetMapping("/getAllSongByKeywords")
    public Map getAllSongByKeywords(String keywords){
        return songService.getAllSongByKeywords(keywords);
    }

    /**
     * 根据歌曲ID查询歌曲对象
     * @param songId 歌曲Id
     * @return
     */
    @GetMapping("/getASongBySongId")
    public Song getASongBySongId(Integer songId){
        return songService.selectSongById(songId);
    }

    /**
     * 修改歌曲信息
     */
    @PostMapping("/updateSongInfo")
    public Map updateSongInfo(@RequestBody Song song){
        Map map = songService.updateSong(song);
        return map;
    }

    /**
     * getASongBySongIdAlias 根据歌曲id带别名查询歌曲信息
     */
    @GetMapping("/getASongBySongIdAlias")
    public Map getASongBySongIdAlias(Integer songId){
        return songService.getASongBySongIdAlias(songId);
    }

    /**
     * 根据主键删除一首歌曲
     */
    @GetMapping("/deleteASong")
    public Map deleteASong(int id){
        Map map = songService.deleteSong(id);
        return map;
    }

    /**
     * 查询歌曲排行榜（按播放量）
     */
    @GetMapping("/getRankList")
    public Map getRankList(){
        Map map = songService.getRankList();
        return map;
    }

    /**
     * 增加播放量
     */
    @GetMapping("/increasePlayCount")
    public Map increasePlayCount(Integer songId){
        Map map = songService.increasePlayCount(songId);
        return map;
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/updateSongPic")
    public Map updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id){
        Map map = new HashMap();
        if(avatorFile.isEmpty()){
            map.put(Consts.CODE, 0);
            map.put(Consts.MESSAGE, "文件为空");
            return map;
        }
        //文件名等于当前时间 + 源文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //去除字符中的所有空白字符，这样是为了防止上传文件进入服务器因为由空格而访问不到对应资源
        fileName.replaceAll("\\s*", "");
        String filePath = Consts.FILE_PATH + Consts.SONG_PIC_PATH;
        File file1 = new File(filePath);
        //如果文件路径不存在，则新增
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件路径：相对路径
        String storeDBPath = Consts.SONG_PIC_PATH + "/" + fileName;
        try {
            //更新前查询出旧文件路径
            Song song1 = songService.selectSongById(id);
            String oldFilePath = song1.getPic();
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeDBPath);
            map = songService.updateSong(song);
            //更新成功后删除旧文件
            if(song1 != null && !oldFilePath.equals(Consts.DEFAULT_SONG_PIC_PATH)){
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
     * 更新歌曲(文件)
     */
    @PostMapping("/updateSong")
    public Map updateSong(@RequestParam("file") MultipartFile musicFile, @RequestParam("id") int id){
        Map map = new HashMap();
        //获取文件类型
        String fileAllName = musicFile.getOriginalFilename();
        String fileType = fileAllName.substring(fileAllName.lastIndexOf("."),fileAllName.length());
        if(musicFile.isEmpty()  || !fileType.equals(".mp3")){
            map.put(Consts.CODE, 0);
            map.put(Consts.MESSAGE, "文件为空或文件格式错误");
            return map;
        }
        //文件名等于当前时间 + 源文件名
        String fileName = System.currentTimeMillis() + musicFile.getOriginalFilename();
        //去除字符中的所有空白字符，这样是为了防止上传文件进入服务器因为由空格而访问不到对应资源
        fileName.replaceAll("\\s*", "");
        String filePath = Consts.FILE_PATH + "/music/song";
        File file1 = new File(filePath);
        //如果文件路径不存在，则新增
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件路径：相对路径
        String storeDBPath = "/music/song/" + fileName;
        try {
            //更新前查询出旧文件路径
            Song song1 = songService.selectSongById(id);
            String oldFilePath = song1.getUrl();
            musicFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeDBPath);
            map = songService.updateSong(song);
            //更新成功后删除旧文件
            if(song1 != null){
                File oldFile = new File(Consts.FILE_PATH + oldFilePath);
                oldFile.delete();
            }
            map.put(Consts.MESSAGE, "上传成功");
            map.put("url",storeDBPath);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put(Consts.MESSAGE, "上传失败"+e.getMessage());
        } finally {
            return map;
        }
    }
}
