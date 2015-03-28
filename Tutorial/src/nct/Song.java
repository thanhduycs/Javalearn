package nct;

public class Song {
    public String name;
    public String url;
    public String singer;

    public String getTruncSongName() {
        String name = this.name.trim();
        name = comwrap.Text.removeVIE(name);
        return name;
    }
    
    public String getSongNameInclueSinger() {
        String fileName = name + "--" + singer;
        fileName = fileName.trim();
        fileName = comwrap.Text.removeVIE(fileName);
        char [] illegals = "~!@#$%^&*()_+|{}[]:;'\"\\/?,".toCharArray(); 
        for(char c : illegals)
        {
            fileName = fileName.replace(c, ' ');
        }
        fileName = fileName.trim()
            .replaceAll(" +", " ")
            .replaceAll(" ", "-")
            .replaceAll("-{3,}", "--")
            .replaceAll("\\.-+", ".")
            .replaceAll("^\\-+", "")
            .replaceAll("\\-+$", "");
        return fileName;
    }
}
