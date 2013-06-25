package de.mag.hypercab.api;

import java.util.Collection;

/**
 * A Table enhancer adds additional information to the table object.
 * 
 * @author marco
 * 
 */
public interface TableEnhancer {

	void enhance(Collection<Table> tables);
}
