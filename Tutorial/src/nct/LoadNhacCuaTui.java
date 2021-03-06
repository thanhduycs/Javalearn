package nct;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadNhacCuaTui {
    private String xmlURL;
    private String dwPath;
    private Exception lastError;

    private List<Song> fileLocations = null;
    private static int index = 0;

    public void parseXML() throws XPathExpressionException,
            ParserConfigurationException, SAXException, IOException {
        DocumentBuilder dBuilder;
        NodeList nodes;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlURL);

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        nodes = (NodeList) xpath.evaluate("//track", doc,
                XPathConstants.NODESET);

        Song song;
        this.fileLocations = new ArrayList<Song>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element elm = (Element) nodes.item(i);
            song = new Song();
            song.url = comwrap.Text.XMLGetTextValue(elm, "location").trim();
            song.name = comwrap.Text.capitalize(
                    comwrap.Text.XMLGetTextValue(elm, "title").trim());
            song.name = song.name.replaceAll("\\?", "");
            song.singer = comwrap.Text.XMLGetTextValue(elm, "creator").trim();
            if ("Đang Cập Nhật".compareToIgnoreCase(song.singer) != 0) {
                song.name += "--" + song.singer;
            }
            this.fileLocations.add(song);
            System.out.println(song.name);
        }
    }

    public void saveUrl(final Song song) {
        String fileName = song.name
                .replaceAll("\\&|/|\\\\|,|'|\"|\\(|\\)|\\[|\\]", " ").trim()
                .replaceAll(" +", " ").replaceAll(" ", "-")
                .replaceAll("-{3,}", "--").replaceAll("\\.-+", ".");
      
        String oldFilePath = String.format("%s/%02d.%s.mp3", dwPath, ++index,
                fileName);
        comwrap.Http.saveUrlSafeIfNotExist(oldFilePath.replace('/', '\\'), song.url);
        System.out.println(oldFilePath);
    }

    public void dowload() {
        int failed = 0;
        for (int i = 0; failed < 3 && i < fileLocations.size(); i++) {
            try {
                Song url = fileLocations.get(i);
                saveUrl(url);
                failed = Math.min(0, failed - 1);
                // Thread.sleep(30*1000);
            } catch (Exception e) {
                e.printStackTrace();
                setLastError(e);
                failed++;
            }
        }
    }

    public String getXmlURL() {
        return xmlURL;
    }

    public void setXmlURL(String xmlURL) {
        this.xmlURL = xmlURL;
    }

    public String getDownloadPath() {
        return dwPath;
    }

    public void setDownloadPath(String dwPath) {
        this.dwPath = dwPath;
    }

    public static void main(String[] args) throws MalformedURLException,
            IOException, XPathExpressionException,
            ParserConfigurationException, SAXException {
        // String proxyHost = System.getProperty("http.proxyHost");
        // System.setProperty("http.proxyHost", "hcm-proxy");
        // System.setProperty("http.proxyPort", "8080");
        System.setProperty("java.net.useSystemProxies", "true");

        LoadNhacCuaTui loader = new LoadNhacCuaTui();
        loader.setXmlURL("http://www.nhaccuatui.com/flash/xml?key2=4bb5edfcd6ad9b86857280b10e004db6");
        loader.setDownloadPath(comwrap.Path.getDesktotPath("music\\nct_thap_nien_70s"));
        loader.parseXML();
        loader.dowload();
    }

    public Exception getLastError() {
        return lastError;
    }

    public void setLastError(Exception lastError) {
        this.lastError = lastError;
    }

}
