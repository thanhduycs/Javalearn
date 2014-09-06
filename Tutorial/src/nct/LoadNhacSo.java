package nct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadNhacSo {
    private String urlXML;
    private String dwPath;
    private Document dom;
    private List<Song> songs;

    public LoadNhacSo() {

    }

    public void setURL(String urlXML) {
        this.urlXML = urlXML;
    }

    public void setDownloadPath(String dwPath) {
        this.dwPath = dwPath;
    }

    public void parseXML() throws ParserConfigurationException, SAXException,
            IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        dom = db.parse(urlXML);
        parseDoc();
    }

    private void parseDoc() {
        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getElementsByTagName("playlist");
        if (nl != null && nl.getLength() > 0) {
            songs = parsePlayList((Element) nl.item(0));
        } else {
            throw new RuntimeException("playlist not found");
        }
    }

    private List<Song> parsePlayList(Element playlistElement) {
        List<Song> songs = new ArrayList<Song>();
        NodeList nl = playlistElement.getElementsByTagName("song");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element) nl.item(i);
                songs.add(parseSong(el));
            }
        }
        return songs;
    }

    private Song parseSong(Element songElement) {
        Song song = new Song();
        song.name = getTextValue(songElement, "name");
        song.url = getTextValue(songElement, "mp3link");
        return song;
    }

    private String getTextValue(Element el, String tagName) {
        return el.getElementsByTagName(tagName).item(0).getTextContent();
    }

    public String getFullSongPath(String songName, int index) {
        String dir = this.dwPath;
        String fileName = songName
                .replaceAll("\\&|/|\\\\|,|'|\"|\\(|\\)|\\[|\\]", " ").trim()
                .replaceAll(" +", " ").replaceAll(" ", "-")
                .replaceAll("-{3,}", "--").replaceAll("\\.-+", ".");
        return String.format("%s/%02d.%s.mp3", dir, index + 1, fileName);
    }

    public int download() {
        int count = 0;
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            String songPath = getFullSongPath(song.getTruncSongName(), i);
            double begin_time = System.currentTimeMillis();
            System.out.printf(">>>>>Fetch: %s\n", songPath);
            System.out.printf("     %s\n", song.url);
            httpcom.Http.saveUrlSafeIfNotExist(songPath, song.url);
            double end_time = System.currentTimeMillis();
            System.out.printf("  done! %.2f minutes\n",
                    (end_time - begin_time) / 60000.0);
            count++;
        }
        return count;
    }

    public int run() throws ParserConfigurationException, SAXException,
            IOException {
        parseXML();
        return download();
    }

    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        System.setProperty("java.net.useSystemProxies", "true");
        
        LoadNhacSo lns = new LoadNhacSo();
        lns.setURL("http://nhacso.net/flash/playlist/xnl/1/uid/X1pXVUNXbQAGAA==,WlpQUUNe,Xg==,1409973506&adsLink=http%3A%2F%2F180.148.142.153%2FCpx.aspx%3Fs%3D39%26r%3D0%26c%3D8%26p%3D604%26n%3D22806581467%26f%3D0%26fm%3D1&typePlayer=playlist&mAuto=true");
        lns.setDownloadPath(httpcom.Path.getDesktotPath("nhacso"));
        lns.run();
    }

}
