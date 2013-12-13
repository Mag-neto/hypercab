package de.mag.hypercab.app.hyperpin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.api.TableEnhancer;
import de.mag.hypercab.app.ServerRegistry;
import de.mag.hypercab.app.hyperpin.database.DatabaseManager;
import de.mag.hypercab.app.hyperpin.database.XmlDatabase;

@Service
public class TableService {

	@Resource
	private ServerRegistry serverRegistry;

	@Resource
	private DatabaseManager databaseManager;

	public Set<Table> getInstalledTables() {
		Set<Table> installedTables = databaseManager.getInstalledTables();
		enhanceTables(installedTables);
		return installedTables;
	}

	public void save() {
		databaseManager.save();
	}

	public void addTable(Table table) {
		databaseManager.addTable(table);
	}

	public void removeTable(Table table) {
		databaseManager.removeTable(table);
	}

	public void updateTable(String description, Table table) {
		databaseManager.updateTable(description, table);
	}

	private void enhanceTables(Collection<Table> tables) {
		for (TableEnhancer enhancer : serverRegistry.getTableEnhancers()) {
			enhancer.enhance(tables);
		}
	}

	public Table toTable(String xmlString) {
		try (InputStream xmlStream = IOUtils.toInputStream(xmlString)) {
			Document game = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlStream);
			return XmlDatabase.createTableFromXmlNode(game.getFirstChild());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

}
