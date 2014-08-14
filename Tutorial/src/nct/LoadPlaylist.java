package nct;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadPlaylist {
	private String xmlData;
	private ArrayList<String> lastListUrls = null;
	public static int index = 0;

	public String loadXML(String urlString) throws MalformedURLException,
			IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		StringBuffer buff = new StringBuffer();
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				buff.append(new String(data, 0, count, "UTF-8"));
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
		return this.xmlData = buff.toString().trim();
	}

	public void parseXML2Urls() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new ByteArrayInputStream(this.xmlData
					.getBytes("UTF-8")));
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("location");

			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			nodes = (NodeList) xpath.evaluate("//location", doc,
					XPathConstants.NODESET);

			this.lastListUrls = new ArrayList<String>();
			for (int i = 0; i < nodes.getLength(); i++) {
				String location = nodes.item(i).getTextContent().trim();
				this.lastListUrls.add(location);
				System.out.println(location);
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

	public void saveUrl(final String urlString) throws MalformedURLException,
			IOException {
		final String OUTDIR = "C:\\Users\\T420\\Desktop\\mp3\\";
		String s = urlString.substring(urlString.lastIndexOf('/') + 1).trim();
		String oldFilePath = OUTDIR + String.format("%02d.", ++index) + s;
		File newFile = new File(oldFilePath);
		File oldFile = new File(OUTDIR + s);
		boolean flag = newFile.exists();
		if (oldFile.exists() && !flag) {
			oldFile.renameTo(newFile);
		} else if (!flag) {
			saveUrl(oldFilePath, urlString);
		}
		System.out.println(oldFilePath);
	}

	public void saveFromLastPlayList() {
		for (String url : this.lastListUrls) {
			try {
				System.out.println(url);
				saveUrl(url);
				// Thread.sleep(30*1000);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public ArrayList<String> LoadListUrls() {
		ArrayList<String> arraylist = new ArrayList<String>(200);
		BufferedReader in = null;
		try {
			String filePath = "C:\\Users\\T420\\Desktop\\nhacquoctehay.xml";
			in = new BufferedReader(new FileReader(filePath));
			while (in.ready()) {
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
		return this.lastListUrls = arraylist;
	}

	public static void main(String[] args) throws MalformedURLException,
			IOException {
		//String proxyHost = System.getProperty("http.proxyHost");
		// System.setProperty("http.proxyHost", "hcm-proxy");
		// System.setProperty("http.proxyPort", "8080");
		System.setProperty("java.net.useSystemProxies", "true");

		LoadPlaylist loader = new LoadPlaylist();
		loader.loadXML("http://www.nhaccuatui.com/flash/xml?key2=e2806067ddd6ae211f3021c2d53b513f");
		loader.parseXML2Urls();
		// loader.LoadListUrls();
		loader.saveFromLastPlayList();
	}

}
