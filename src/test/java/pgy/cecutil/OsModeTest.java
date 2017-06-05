package pgy.cecutil;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.junit.Test;

public class OsModeTest {
	
	@Test
	public void testOsMode() throws FileNotFoundException, AWTException {
		OsMode mode = new OsMode();
		mode.start(TestUtil.getTestInputStream());
	}
}
