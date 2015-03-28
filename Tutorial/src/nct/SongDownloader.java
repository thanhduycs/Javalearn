package nct;

import java.util.List;

public class SongDownloader {
    private List<Song> songs;
    private String saveFolder;
    
    private boolean isDownload(Song song, String songPath) {
        return false;
    }

    public void download(java.util.List<Song> songs, String folder) {
        this.songs = songs;
        this.saveFolder = folder;
        
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            String songPath = String.format("%s\\%02d.%s.mp3", 
                    this.saveFolder, 
                    i+1,
                    song.getSongNameInclueSinger());
            if(!isDownload(song, songPath)) {
                System.out.println(String.format("%s\n--> %s", songPath, song.url));
                comwrap.Http.saveUrlSafeIfNotExist(songPath, song.url);
            }
        }
    }
}
