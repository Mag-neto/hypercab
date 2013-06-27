package de.mag.hypercab.app;

import java.util.Collection;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.api.TableEnhancer;

@Component
public class TestEnhancer implements TableEnhancer {

	@Override
	public void enhance(Collection<Table> tables) {

	}

}
