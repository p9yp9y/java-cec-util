package pgy.cecutil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;

import pgy.cecutil.CecEvent.Type;

public class OsMode {

	private CecService service;
	private Robot robot;

	public void start(InputStream inputStream) throws AWTException {
		robot = new Robot();
		service = new CecService(inputStream);
		service.addListener(e -> {
			handle(e);
		});
		service.start();
	}

	private void handle(CecEvent e) {
		Type type = e.getType();
		if (type.equals(CecEvent.Type.KEY_PRESSED)) {
			int keycode = getKeycode(e.getData());
			robot.keyPress(keycode);
		} else if (type.equals(CecEvent.Type.KEY_RELEASED)) {
			int keycode = getKeycode(e.getData());
			robot.keyPress(keycode);
		}
	}

	private int getKeycode(String data) {
		switch (data) {
		case "0":
			return KeyEvent.VK_0;
		case "1":
			return KeyEvent.VK_1;
		case "2":
			return KeyEvent.VK_2;
		case "3":
			return KeyEvent.VK_3;
		case "4":
			return KeyEvent.VK_4;
		case "5":
			return KeyEvent.VK_5;
		case "6":
			return KeyEvent.VK_6;
		case "7":
			return KeyEvent.VK_7;
		case "8":
			return KeyEvent.VK_8;
		case "9":
			return KeyEvent.VK_9;
		case "previous channel":
			return KeyEvent.VK_BACK_SPACE;
		case "select":
			return KeyEvent.VK_ENTER;
		case "channel up":
			return KeyEvent.VK_PAGE_UP;
		case "channel down":
			return KeyEvent.VK_PAGE_DOWN;
		case "up":
			return KeyEvent.VK_UP;
		case "down":
			return KeyEvent.VK_DOWN;
		case "left":
			return KeyEvent.VK_LEFT;
		case "right":
			return KeyEvent.VK_RIGHT;
		case "F1":
			return KeyEvent.VK_F1;
		case "F2":
			return KeyEvent.VK_F2;
		case "F3":
			return KeyEvent.VK_F3;
		case "F4":
			return KeyEvent.VK_F4;
		default:
			return -1;
		}
	}

}
