package javacommon.base;

public class Suggestion {
	private String value;
	private String data;
	
	public Suggestion(){}
	
	public Suggestion(String value, String data) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.data = data;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
