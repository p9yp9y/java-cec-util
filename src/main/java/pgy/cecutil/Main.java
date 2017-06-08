package pgy.cecutil;

import java.awt.AWTException;
import java.io.IOException;

import com.beust.jcommander.Parameter;

import pgy.master.util.JCommanderUtil;

public class Main {
	@Parameter(names = { "--osmode", "-osmode" }, description="OS mode")
	boolean osmode;

	@Parameter(names = { "--target", "-target" }, description="target")
	String target;

	private Process process;

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		JCommanderUtil.parseArgs(main, args);
		main.run();
		
		System.in.read();
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