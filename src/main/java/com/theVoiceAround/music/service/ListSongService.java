package com.theVoiceAround.music.service;

import com.theVoiceAround.music.entity.ListSong;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/3 16:20
 * @description 歌单歌曲Service
 */
public interface ListSongService {
    Map getListSong(Integer songListId);

    Map addSongToSongList(ListSong listSong);

    Map deleteASongFromSongList(Integer songListId, Integer songId);
}
