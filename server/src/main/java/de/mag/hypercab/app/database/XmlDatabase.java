package de.mag.hypercab.app.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.mag.hypercab.api.Table;

public class XmlDatabase {

	private static final String TYPE_TAG = "type";
	private static final String YEAR_TAG = "year";
	private static final String MANUFACTURER_TAG = "manufacturer";
	private static final String DESCRIPTION_TAG = "description";
	private static final String NAME_ATTRIBUTE = "name";
	private static final String GAME_TAG = "game";

	static Map<String, Table> readDb(File dbFile) {
		Document document;
		try {
			document = readXmlDocument(dbFile);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new DatabaseException("Error reading database file " + dbFile.getName(), e);
		}
		Map<String, Table> tableList = new TreeMap<String, Table>();
		NodeList gamesNodes = document.getElementsByTagName(GAME_TAG);
		for (int i = 0; i < gamesNodes.getLength(); i++) {
			NodeList childNodes = gamesNodes.item(i).getChildNodes();
			String gameName = gamesNodes.item(i).getAttributes().getNamedItem(NAME_ATTRIBUTE)
					.getTextContent();
			Table table = new Table();
			table.setFileName(gameName);
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node item = childNodes.item(j).getNextSibling();
				if (item != null) {
					if (item.getNodeName().equals(DESCRIPTION_TAG)) {
						table.setDescription(item.getTextContent());
					} else if (item.getNodeName().equals(MANUFACTURER_TAG)) {
						table.setManufacturer(item.getTextContent());
					} else if (item.getNodeName().equals(YEAR_TAG)) {
						table.setYear(item.getTextContent());
					} else if (item.getNodeName().equals(TYPE_TAG)) {
						table.setMachineType(item.getTextContent());
					}
				}
			}
			tableList.put(table.getDescription(), table);
		}
		return tableList;
	}

	private static Document readXmlDocument(File dbFile) throws SAXException, IOException,
			ParserConfigurationException {
		if (!dbFile.exists()) {
			createEmptyDbFile(dbFile);
		}
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(dbFile);
	}

	private static void createEmptyDbFile(File dbFile) throws IOException {
		try (OutputStream outStream = new FileOutputStream(dbFile)) {
			outStream.write("<menu>\n</menu>\n".getBytes("UTF-8"));
			outStream.flush();
		}
	}

	static void writeDb(File dbFile, List<Table> tables) {
		try (OutputStream outStream = new FileOutputStream(dbFile.getAbsolutePath() + ".new");) {
			outStream.write("<menu>\n".getBytes("UTF-8"));
			for (Table table : tables) {
				outStream.write(toXmlString(table).getBytes("UTF-8"));
			}
			outStream.write("</menu>\n".getBytes("UTF-8"));
			outStream.flush();
		} catch (Exception e) {
			throw new DatabaseException("Error writing database " + dbFile.getName(), e);
		}
		try {
			File lastFile = new File(dbFile.getAbsolutePath() + ".last");
			dbFile.renameTo(lastFile);
			new File(dbFile.getAbsolutePath() + ".new").renameTo(dbFile);
			lastFile.delete();
		} catch (Exception e) {
			throw new DatabaseException("Error renaming db files " + dbFile.getName(), e);
		}
	}

	private static String toXmlString(Table table) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<game name=\"").append(table.getFileName()).append("\">")
				.append("\n<description>").append(table.getDescription()).append("</description>")
				.append("\n<manufacturer>").append(table.getManufacturer()).append("</manufacturer>")
				.append("\n<year>").append(table.getYear()).append("</year>").append("\n<type>")
				.append(table.getMachineType()).append("</type>");
		sb.append("\n</game>");
		return sb.toString();
	}

}
