package pgy.cecutil;

public class CecService {
	private static CecService instance;

	public static CecService start() {
		instance = new CecService();
		return instance;
	}
}
