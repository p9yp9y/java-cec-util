package pgy.cecutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestUtil {
	public static InputStream getTestInputStream() throws FileNotFoundException {
		return new FileInputStream(new File("cec_util_log.txt"));
	}
}
