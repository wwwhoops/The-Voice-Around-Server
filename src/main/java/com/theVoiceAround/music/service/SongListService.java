package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.SongList;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/3 10:19
 * @description 歌单Service
 */
public interface SongListService {
    Map addSongList(SongList songList);

    Map getAllSongListPage(int pageSize, int pageNum, String title);

    SongList selectSongListById(Integer id);

    Map deleteSongList(Integer id);

    Map updateSongList(SongList songList);
}
