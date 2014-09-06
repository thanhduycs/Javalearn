package nct;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadZingRadio {
	private Document doc = null;
	private ArrayList<String[]> lastListUrls = null;

	private String xmlURL;
	private String mp3Path;

	public LoadZingRadio(String xmlUrl, String mp3Path) {
		this.xmlURL = xmlUrl;
		this.mp3Path = mp3Path;
	}

	private Document getXMLDocument(String urlString)
			throws MalformedURLException, IOException,
			ParserConfigurationException, SAXException {
		Document doc = null;
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(in);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return this.doc = doc;
	}

	private void parseDocToURL() throws XPathExpressionException {
		doc.getDocumentElement().normalize();
		NodeList nodes = doc.getElementsByTagName("location");

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		nodes = (NodeList) xpath.evaluate("//item ", doc,
				XPathConstants.NODESET);

		this.lastListUrls = new ArrayList<String[]>();
		for (int i = 0; i < nodes.getLength(); i++) {

			Node item = nodes.item(i);
			NodeList nl = item.getChildNodes();

			String strTitle = null;
			String strSource = null;
			for (int k = 0; k < nl.getLength(); k++) {
				Node it = nl.item(k);
				if (it.getNodeName().equals("title"))
					strTitle = it.getTextContent().trim();
				else if (it.getNodeName().equals("source"))
					strSource = it.getTextContent().trim();
			}

			if (strTitle != null && strSource != null) {
				strTitle = removeVIE(strTitle).trim();
				strTitle = strTitle.replaceAll("\\(|\\)", "");
				strTitle = strTitle.replaceAll("So Tay Cam Xuc\\s*", "");
				strTitle = strTitle.replaceAll("^Ky ", "Ky");
				strTitle = strTitle.replaceAll("^So ", "Ky");
				strTitle = strTitle.replaceAll(":\\s*", ".");
				strTitle = strTitle.replaceAll("\\s+", "-");
				this.lastListUrls.add(new String[] { String.valueOf(i),
						strTitle, strSource });
				System.out.println(strTitle);
				System.out.println(strSource);
			}
		}
	}

	public void saveUrl(final String filename, final String urlString)
			throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			fout = new FileOutputStream(filename);

			int kb = 0;

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
				if (++kb % 100 == 0)
					System.out.print(".");
				if (kb % 2000 == 0)
					System.out.println();
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

	public void saveUrlIfNotExist(final String filename, final String urlString)
			throws MalformedURLException, IOException {
		File newFile = new File(filename);
		boolean flag = newFile.exists();
		if (!flag) {
			saveUrl(filename, urlString);
		}
	}

	private void downloadAll() throws MalformedURLException, IOException {
		for (String[] toks : this.lastListUrls) {
			String strTitle = toks[1];
			String strSource = toks[2];
			String fileName = String
					.format("%s/%s.mp3", this.mp3Path, strTitle);
			saveUrlIfNotExist(fileName, strSource);
		}
	}

	public void run() {
		try {
			this.getXMLDocument(this.xmlURL);
			this.parseDocToURL();
			this.downloadAll();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String removeVIE(String strVie) {
		final String SPECIAL_CHARACTERS = "àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰýÝỳỲỷỶỹỸỵỴ";
		final String REPLACEMENTS = "aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeEiIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOuUuUuUuUuUuUuUuUuUuUuUyYyYyYyYyYyY";

		HashMap<Character, Character> hashMap = new HashMap<Character, Character>(
				SPECIAL_CHARACTERS.length());
		for (int i = 0; i < SPECIAL_CHARACTERS.length(); i++) {
			hashMap.put(SPECIAL_CHARACTERS.charAt(i), REPLACEMENTS.charAt(i));
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (Character chr : strVie.toCharArray()) {
			Character newChr = hashMap.get(chr);

			if (newChr != null)
				stringBuilder.append(newChr);
			else
				stringBuilder.append(chr);

		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
	    System.setProperty("java.net.useSystemProxies", "true");
	    
		final String desktopPath = System.getProperty("user.home") + "/Desktop";

		final String xmlUrl = "http://radio.zing.vn/xml/radio/kHcHtLnadsAFmdayZvJybmLH?seq=1";
		final String mp3Path = desktopPath + "/zingRadio";
		LoadZingRadio radio = new LoadZingRadio(xmlUrl, mp3Path);
		radio.run();
	}

}
