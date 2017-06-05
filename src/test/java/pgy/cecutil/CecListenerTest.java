package pgy.cecutil;

import java.io.IOException;

import org.junit.Test;

public class CecListenerTest {
	
	@Test
	public void testEvent() throws IOException, InterruptedException {
		CecService s = new CecService(TestUtil.getTestInputStream());
		s.addListener(l -> {
			System.out.println(l);
		});
		s.start();
		
		Thread.sleep(100000);
	}
}
