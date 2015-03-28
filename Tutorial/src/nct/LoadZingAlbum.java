package nct;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadZingAlbum {
    private String mMp3URL;
    private String mMp3HTMLResponse;
    private String dataURL;
    private String dataHTMLResponse;
    
    private ArrayList<Song> songs = null;

    private void loadData() {
        mMp3HTMLResponse = comwrap.Http.readURLSafe(mMp3URL);

        Pattern pattern = Pattern.compile("(http://m.mp3.zing.vn\\/xml\\/album\\/\\w+)");
        Matcher matcher = pattern.matcher(mMp3HTMLResponse);
        
        if (matcher.find()) {
            dataURL = matcher.group(1);
            System.out.println(dataURL);
            dataHTMLResponse = comwrap.Http.readURLSafe(dataURL);
            System.out.println(dataHTMLResponse); 
            this.parseData();
        } else {
            System.out.println("CAN NOT DECTECT dataURL");
            System.out.println(mMp3HTMLResponse);
        }
    }
    
    private  void  parseData() {
        JSONParser parser=new JSONParser();
        try {
            JSONObject jRootObj  = (JSONObject)parser.parse(this.dataHTMLResponse);
            JSONArray jArrData = (JSONArray) jRootObj.get("data");
            for (Object object : jArrData) {
                JSONObject jObjSong = (JSONObject)object;
                Song newSong = new Song();
                newSong.name = (String) jObjSong.get("title");
                newSong.singer = (String)jObjSong.get("performer");
                newSong.url = (String)jObjSong.get("source");
                System.out.println(newSong.getSongNameInclueSinger());
                this.songs.add(newSong);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void load(String url) {
        this.songs = new ArrayList<Song>();
        this.mMp3URL = url;
        this.dataURL = null;

        this.loadData();
    }

    public static void main(String[] args) {
        LoadZingAlbum loader = new LoadZingAlbum();
        loader.load("http://m.mp3.zing.vn/playlist/Nhung-ca-khuc-bat-hu-thap-nien-70-80-mrhaostyle/IWC09ZEB.html?st=21");
        
        SongDownloader songDownloader = new SongDownloader();
        songDownloader.download(loader.getSongs(), comwrap.Path.getDesktotPath("music\\zing_nhac"));
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
