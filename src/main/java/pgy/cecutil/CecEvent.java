package pgy.cecutil;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CecEvent {
	public enum Type {
		KEY_PRESSED,
		KEY_RELEASED,
		POWER_STATUS_CHANGED
	}
	
	private Type type;
	
	private String data;

	public CecEvent(Type type, String data) {
		super();
		this.type = type;
		this.data = data;
	}

	public Type getType() {
		return type;
	}

	public String getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
