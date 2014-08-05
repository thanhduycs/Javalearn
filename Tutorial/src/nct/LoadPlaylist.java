package nct;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class LoadPlaylist {
	
	private ArrayList<String> lastListUrls = null;
	public static int index = 0;
	
	
	public void saveUrl(final String filename, final String urlString)
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
	    } finally {
	        if (in != null) {
	            in.close();
	        }
	        if (fout != null) {
	            fout.close();
	        }
	    }
	}
	
	public void saveUrl(final String urlString) throws MalformedURLException, IOException
	{
		final String OUTDIR = "C:\\Users\\DuyNT9\\Desktop\\mp3\\";
		String s = urlString.substring(urlString.lastIndexOf('/')+1).trim();
		String oldFilePath = OUTDIR + String.format("%02d.", ++index) + s;
		File newFile = new File(oldFilePath);
		File oldFile = new File(OUTDIR + s);
		boolean flag = newFile.exists();
		if (oldFile.exists() && !flag)
		{
			oldFile.renameTo(newFile);
		} else if (!flag) 
		{
			saveUrl(oldFilePath, urlString);
		}
		System.out.println(oldFilePath);
	}
	
	public void saveFromLastPlayList()
	{
		for (String url : this.lastListUrls) {
			try {
				System.out.println(url);
				saveUrl(url);
				//Thread.sleep(30*1000);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> LoadListUrls()
	{
		ArrayList<String> arraylist = new ArrayList<String>(200);
		BufferedReader in = null;
		try {
			String filePath = "C:\\Users\\DuyNT9\\Desktop\\nhacquoctehay.xml";
			in = new BufferedReader(new FileReader(filePath));
			while(in.ready())
			{
				String line = in.readLine();
				arraylist.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return this.lastListUrls =  arraylist;
	}
	

	public static void main(String[] args) {
		String proxyHost = System.getProperty("http.proxyHost");
		
		System.setProperty("http.proxyHost", "hcm-proxy");
		System.setProperty("http.proxyPort", "8080");
		//System.setProperty("java.net.useSystemProxies", "true");
		
		LoadPlaylist loader = new LoadPlaylist();
		loader.LoadListUrls();
		loader.saveFromLastPlayList();
	}

}
