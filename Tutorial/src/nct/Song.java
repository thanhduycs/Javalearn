package nct;

public class Song {
    public String name;
    public String url;
    public String singer;

    public String getTruncSongName() {
        String name = this.name.trim();
        name = httpcom.Text.removeVIE(name);
        return name;
    }
}
