package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.Song;

import java.util.List;
import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/2/22 17:46
 * @description 歌曲Service
 */
public interface SongService {

    Map addSong(Song song);

    Map selectSingerSongPage(int pageSize, int pageNum, String songName, int singerId);

    Map selectAllSongBySingerId(Integer singerId);

    Map updateSong(Song song);

    Map deleteSong(Integer id);

    Song selectSongById(Integer id);

    List deleteSongBySingerId(Integer id);

    Map selectAllSong();

    Map getAllSongByKeywords(String keywords);

    Map selectAllSongBySingerIdAlias(Integer singerId);

    Map getASongBySongIdAlias(Integer songId);

    Map getRankList();

    Map increasePlayCount(Integer songId);
}
