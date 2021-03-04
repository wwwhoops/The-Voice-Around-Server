package com.theVoiceAround.music.controller;

import com.theVoiceAround.music.entity.ListSong;
import com.theVoiceAround.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/3 16:18
 * @description 歌单歌曲管理Controller
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 根据歌单id查询歌曲id id, song_id, songList_id
     *   SELECT
     *   *
     *   FROM
     *   list_song
     *   LEFT JOIN
     *   song ON song.id = list_song.song_id
     *   WHERE
     *   list_song.song_list_id = songListId
     */
    @GetMapping("/getListSong")
    public Map getListSong(Integer songListId){
        //根据歌单id查询歌曲信息(多条记录)
        return listSongService.getListSong(songListId);
    }

    /**
     * 根据歌单id，歌曲id，添加歌曲至歌单
     */
    @PostMapping("/addSongToSongList")
    public Map addSongToSongList(@RequestBody ListSong listSong){
        return listSongService.addSongToSongList(listSong);
    }

    /**
     * 从歌单中删除一首歌曲
     */
    @GetMapping("/deleteASongFromSongList")
    public Map deleteASongFromSongList(Integer songListId, Integer songId){
        return listSongService.deleteASongFromSongList(songListId, songId);
    }

}
