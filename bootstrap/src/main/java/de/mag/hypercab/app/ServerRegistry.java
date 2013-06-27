package de.mag.hypercab.app;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.HypercabModule;
import de.mag.hypercab.api.TableEnhancer;

/**
 * Class for auto discovery of available {@link HypercabModule}s and
 * {@link TableEnhancer}s.<br>
 * Every implementation of either interface annotated with @Component will be
 * discovered and automatically added to the registry on server startup.
 * 
 * @author marco
 * 
 */
@Component
public class ServerRegistry {

	@Resource
	private List<HypercabModule> hypercabModules;

	@Resource
	private List<TableEnhancer> tableEnhancers;

	public List<HypercabModule> getRegisteredModules() {
		return hypercabModules;
	}

	public List<TableEnhancer> getTableEnhancers() {
		return tableEnhancers;
	}

}
