package pgy.cecutil;

import java.awt.AWTException;
import java.io.IOException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
	@Parameter(names = "--help", help = true)
	private boolean help;
	
	@Parameter(names = { "--osmode", "-osmode" }, description="OS mode")
	boolean osmode;

	@Parameter(names = { "--target", "-target" }, description="target")
	String target;

	private Process process;

	private CecService service;

	public static void main(String... argv) {
		Main main = new Main();
		JCommander build = JCommander.newBuilder().addObject(main).build();
		build.parse(argv);
		if(main.help) {
			build.usage();
			return;
		}
		main.run();
	}

	public void run() {
		try {
			process = Runtime.getRuntime().exec("cec-client " + target);
			if (osmode) {
				try {
					new OsMode().start(process.getInputStream());
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}