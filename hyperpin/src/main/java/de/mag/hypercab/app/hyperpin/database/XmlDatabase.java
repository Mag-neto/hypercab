package de.mag.hypercab.app.hyperpin.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
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

	private static final String GAME_TAG = "game";
	private static final String NAME_ATTRIBUTE = "name";
	private static final String DESCRIPTION_TAG = "description";
	private static final String MANUFACTURER_TAG = "manufacturer";
	private static final String YEAR_TAG = "year";
	private static final String TYPE_TAG = "type";

	static Map<String, Table> readFromFile(File dbFile) {
		Document document = loadXmlFile(dbFile);
		Map<String, Table> tables = new TreeMap<String, Table>();
		NodeList tableNodes = document.getElementsByTagName(GAME_TAG);
		for (int i = 0; i < tableNodes.getLength(); i++) {
			Table table = createTableFromXmlNode(tableNodes.item(i));
			tables.put(table.getDescription(), table);
		}
		return tables;
	}

	private static Document loadXmlFile(File dbFile) {
		try {
			if (!dbFile.exists()) {
				createEmptyDbFile(dbFile);
			}
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(dbFile);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new DatabaseException("Error reading database file " + dbFile.getName(), e);
		}
	}

	private static void createEmptyDbFile(File dbFile) throws IOException {
		try (OutputStream outStream = new FileOutputStream(dbFile)) {
			outStream.write("<menu></menu>".getBytes("UTF-8"));
			outStream.flush();
		}
	}

	private static Table createTableFromXmlNode(Node tableNode) {
		Table table = new Table();
		table.setFileName(getTableNameFromNode(tableNode));
		NodeList attributeNodes = tableNode.getChildNodes();
		for (int j = 0; j < attributeNodes.getLength(); j++) {
			Node tableAttribute = attributeNodes.item(j).getNextSibling();
			setAsTableAttribute(tableAttribute, table);
		}
		return table;
	}

	private static String getTableNameFromNode(Node node) {
		return node.getAttributes().getNamedItem(NAME_ATTRIBUTE).getTextContent();
	}

	private static void setAsTableAttribute(Node attribute, Table table) {
		if (attribute != null) {
			if (attribute.getNodeName().equals(DESCRIPTION_TAG)) {
				table.setDescription(attribute.getTextContent());
			} else if (attribute.getNodeName().equals(MANUFACTURER_TAG)) {
				table.setManufacturer(attribute.getTextContent());
			} else if (attribute.getNodeName().equals(YEAR_TAG)) {
				table.setYear(attribute.getTextContent());
			} else if (attribute.getNodeName().equals(TYPE_TAG)) {
				table.setMachineType(attribute.getTextContent());
			}
		}
	}

	static void storeToFile(File dbFile, Collection<Table> tables) {
		storeTablesToNewFile(dbFile, tables);
		renameAndDeleteOldFile(dbFile);
	}

	private static void storeTablesToNewFile(File dbFile, Collection<Table> tables) {
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
	}

	private static void renameAndDeleteOldFile(File dbFile) {
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
		sb.append(buildStartGameTag(table.getFileName()))
				.append(buildAttributeTag(DESCRIPTION_TAG, table.getDescription()))
				.append(buildAttributeTag(MANUFACTURER_TAG, table.getManufacturer()))
				.append(buildAttributeTag(YEAR_TAG, table.getYear()))
				.append(buildAttributeTag(TYPE_TAG, table.getMachineType()));
		sb.append(buildEndGameTag());
		return sb.toString();
	}

	private static String buildStartGameTag(String tableFileName) {
		return "\n<game name=\"" + tableFileName + "\">";
	}

	private static String buildAttributeTag(String attribute, String value) {
		return "\n<" + attribute + ">" + value + "</" + attribute + ">";
	}

	private static String buildEndGameTag() {
		return "\n</game>";
	}

}
