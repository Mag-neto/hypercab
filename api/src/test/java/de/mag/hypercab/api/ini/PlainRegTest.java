package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

import org.ini4j.Reg;
import org.testng.annotations.Test;

public class PlainRegTest {

	@Test(enabled = false)
	public void readWriteRegFile() throws IOException {

		File testFile = new File("target/test-classes/vpinmame.reg");
		Reg reg = new Reg();
		reg.getConfig().setEmptyOption(true);
		reg.load(testFile);
		reg.store(testFile);
	}

}
