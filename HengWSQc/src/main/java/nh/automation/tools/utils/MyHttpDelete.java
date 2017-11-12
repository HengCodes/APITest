package nh.automation.tools.utils;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * MyHttpDelete
 * @author Eric
 * @date 2017年3月12日 nh.automation.tools.emums
 */
public class MyHttpDelete extends HttpEntityEnclosingRequestBase {

	@Override
	public String getMethod() {
		return "DELETE";
	}

	public MyHttpDelete(final String url) {
		super();
		setURI(URI.create(url));
	}

	public MyHttpDelete(final URI url) {
		super();
		setURI(url);
	}

	public MyHttpDelete() {
		super();

	}

}
