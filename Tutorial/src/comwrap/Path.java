package comwrap;

public class Path {
    public static String getDesktotPath(String childDir){
        String desktopPath = System.getProperty("user.home") + "/Desktop/" + childDir;
        return desktopPath;
    }
}
