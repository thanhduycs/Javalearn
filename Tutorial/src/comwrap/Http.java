package comwrap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public final class Http {
    private static Exception  lastError;
    private static String defaultEncoding = "UTF-8";
    
    private Http() {}
    
    public static String readURL(String urlString) throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        StringBuffer buff = new StringBuffer();
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                buff.append(new String(data, 0, count, defaultEncoding));
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
        return buff.toString();
    }
    
    public static String readURLSafe(String urlString) {
        try {
            return readURL(urlString);
        } catch (Exception e) {
            lastError= e;
            e.printStackTrace();
        }
        return null;
    }
    
    public static int saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        }
         catch (IOException  e) {
            System.out.println("Error download file " + filename + " ==> " + e.getMessage());
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
        return 0;
    }
    
    public static int saveUrlSafe(final String filename, final String urlString)
    {
        try {
            return saveUrl(filename, urlString);
        } catch (Exception e) {
            lastError = e;
            e.printStackTrace();
        }
        return 1;
    }
    
    public static int saveUrlSafeIfNotExist(final String filename, final String urlString) {
        if (!new File(filename).exists()) {
            return saveUrlSafe(filename, urlString);
        }
        return 0;
    }
    
    public Exception getLastError() {
        return lastError;
    }

    public static String getDefaultEncoding() {
        return defaultEncoding;
    }

    public static void setDefaultEncoding(String defaultEncoding) {
        Http.defaultEncoding = defaultEncoding;
    }
}


