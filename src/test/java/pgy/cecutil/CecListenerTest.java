package pgy.cecutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class CecListenerTest {
	
	@Test
	public void testEvent() throws IOException, InterruptedException {
		CecService s = new CecService(new FileInputStream(new File("cec_util_log.txt")));
		s.addListener(l -> {
			System.out.println(l);
		});
		s.start();
		
		Thread.sleep(100000);
	}
}
