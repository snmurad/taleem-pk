package com.example.application.backend;

import com.example.application.database.Database;

public class Playlist {

    String cID;
    String Chapter;
    String videoTitle;
    String videoLoc;
    Database data = new Database();

    public Playlist(String _cID, String _Chapter, String _videoTitle, String _videoLoc)
    {
        cID = _cID;
        Chapter = _Chapter;
        videoTitle = _videoTitle;
        videoLoc = _videoLoc;
    }

    public void registerPlaylist()
    {
        data.registerPlaylist(cID, Chapter, videoTitle, videoLoc);
    }

}
