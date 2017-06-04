package pgy.cecutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CecService {
	private boolean connected;
	
	private Set<CecListener> listeners = new HashSet<>();

	private InputStream in;
	
	public CecService(InputStream in) {
		this.in = in;
	}

	private void processLine(String line) {
		String data;
		CecEvent event = null;
		if ((data = find(line, "key pressed: ", " \\(")) != null) {
			event = new CecEvent(CecEvent.Type.KEY_PRESSED, data);
		} else if ((data = find(line, "key released: ", " \\(")) != null) {
			event = new CecEvent(CecEvent.Type.KEY_RELEASED, data);
		} else if ((data = find(line, "power status changed", "$")) != null) {
			event = new CecEvent(CecEvent.Type.POWER_STATUS_CHANGED, data.split("to ")[1].replaceAll("'", ""));
		}
		if (event != null) {
			final CecEvent sentEvent = event;
			listeners.forEach(l -> l.event(sentEvent));
		}
	}

	private String find(String line, String splitter1, String splitter2) {
		String[] cells = line.split(splitter1);
		return cells.length > 1 ? cells[1].split(splitter2)[0] : null;
	}

	public void start() {
		System.out.println("START");

		ExecutorService executor = Executors.newSingleThreadExecutor();
		connected = true;
		executor.submit(() -> {				
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (connected) {
				try {
					processLine(reader.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addListener(CecListener cecListener) {
		listeners.add(cecListener);
	}
	
	public void removeListener(CecListener cecListener) {
		listeners.remove(cecListener);
	}
}
