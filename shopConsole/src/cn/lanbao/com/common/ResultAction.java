
package cn.lanbao.com.common;


public class ResultAction<T> {
	private static final long serialVersionUID = 5454155825314635342L;


	private T data;
	public T getData() {
		return data;
	}

	public void setData(T value) {
		this.data = value;
	}
	
	private boolean iserror;
	public boolean getIserror(){
		return iserror;
	}
	public void setIserror(boolean value)
	{
		this.iserror=value;
	}
	
	private String message;
	public String getMessage(){
		return message;
	}
	public void setMessage(String value)
	{
		this.message=value;
	}
}

