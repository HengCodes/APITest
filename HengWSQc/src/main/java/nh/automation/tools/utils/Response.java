package nh.automation.tools.utils;

import org.apache.http.Header;

/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * Response
 * @author Eric
 * @date 2017年3月12日 nh.automation.tools.emums
 */
public class Response {
	private int statusCode=200;
	private String statusLine="OK";
	private Header[] headers=null;
private String bodyEntity= null;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusLine() {
		return statusLine;
	}
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers =  headers;
	}
	public String getBodyEntity() {
		return bodyEntity;
	}
	public void setBodyEntity(String bodyEntity) {
		this.bodyEntity = bodyEntity;
}
}
