package de.mag.hypercab.app.server;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.api.TableEnhancer;

@Component
public class DummyTableEnhancer implements TableEnhancer {

	// TODO: Remove this as soon as there is at least one TableEnhancer in the
	// applicationContext
	@Override
	public void enhance(Table table) {

	}

}
