package nh.automation.tools.utils;

import java.nio.charset.Charset;

import org.apache.commons.httpclient.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 项目 ：UI自动化测试 SSM 类描述： HttpClientUtil
 * 
 * @author Eric
 * @date 2017年3月12日 nh.automation.tools.emums
 */
public class HttpClientUtil {

	/**
	 * 
	 * @author nh
	 * @date 
	 *       <p>
	 *       get response <／p>
	 * @param url
	 * @param httpMethod
	 * @param headerStr
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public static Response callResponse(String url, String httpMethod, String headerStr, String postData)
			throws Exception {
		url = url.replace(" ", "%20").replace("|", "%7c").replace("[", "%5b").replace("]", "%5d").replace("{", "%7b")
				.replace("}", "%7d").replace("\"", "%22");
		Response response = new Response();

		// http method
		if (httpMethod == null || httpMethod.trim().isEmpty()) {
			httpMethod = "GET";
		}

		// header
		Header[] headers = null;
		if (headerStr != null && !headerStr.equals("")) {
			String[] keyValuePairArray = headerStr.split("\\|\\|");
			headers = new Header[keyValuePairArray.length];

			for (int i = 0; i < keyValuePairArray.length; i++) {
				String[] keyValuePair = keyValuePairArray[i].split(":");

				try {
					headers[i] = new Header(keyValuePair[0],
							keyValuePairArray[i].substring(keyValuePair[0].length() + 1));
				} catch (Exception e) {
					headers[i] = new Header(keyValuePair[0], "");
				}
			}

		}

		if (!httpMethod.equals("GET")) {
			response = doRequest(url, httpMethod, headers, postData);
		} else {
			postData = null;
			response = doRequest(url, httpMethod, headers, postData);
		}

		return response;

	}

	/**
	 * 
	 * @author niuh
	 * @date Jun 20, 2017
	 *       <p>
	 *       do request url <／p>
	 * @param url
	 * @param httpMethod
	 * @param headerStr
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public static Response doRequest(String url, String httpMethod, Header[] headers, String postData)
			throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		Response response = new Response();
		CloseableHttpResponse httpResponse = null;

		if (httpMethod == null || httpMethod.trim().isEmpty()) {
			httpMethod = "GET";
		}

		if (httpMethod.equals("GET")) {
			HttpGet httpGet = new HttpGet(url);
			httpResponse = httpClient.execute(httpGet);
			response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			response.setStatusLine(httpResponse.getStatusLine().getReasonPhrase());
			response.setHeaders(httpResponse.getAllHeaders());
			response.setBodyEntity(EntityUtils.toString(httpResponse.getEntity()));

			return response;
		}

		if (httpMethod.equals("POST")) {
			HttpPost httpPost = new HttpPost(url);
			for (Header header : headers) {
				httpPost.addHeader(header.getName(), header.getValue());
			}
			StringEntity entity = new StringEntity(postData, Charset.forName("UTF-8"));
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			response.setStatusLine(httpResponse.getStatusLine().getReasonPhrase());
			response.setHeaders(httpResponse.getAllHeaders());
			response.setBodyEntity(EntityUtils.toString(httpResponse.getEntity()));

			return response;
		}

		if (httpMethod.equals("PUT")) {
			HttpPut httpPut = new HttpPut(url);
			for (Header header : headers) {
				httpPut.addHeader(header.getName(), header.getValue());
			}
			StringEntity entity = new StringEntity(postData, Charset.forName("UTF-8"));
			httpPut.setEntity(entity);
			httpResponse = httpClient.execute(httpPut);
			response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			response.setStatusLine(httpResponse.getStatusLine().getReasonPhrase());
			response.setHeaders(httpResponse.getAllHeaders());
			response.setBodyEntity(EntityUtils.toString(httpResponse.getEntity()));

			return response;

		}

		if (httpMethod.equals("DELETE")) {
			MyHttpDelete httpDelete = new MyHttpDelete(url);
			for (Header header : headers) {
				httpDelete.addHeader(header.getName(), header.getValue());
			}
			StringEntity entity = new StringEntity(postData, Charset.forName("UTF-8"));
			httpDelete.setEntity(entity);
			httpResponse = httpClient.execute(httpDelete);
			response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			response.setStatusLine(httpResponse.getStatusLine().getReasonPhrase());
			response.setHeaders(httpResponse.getAllHeaders());
			response.setBodyEntity(EntityUtils.toString(httpResponse.getEntity()));

			return response;
		}

		return response;

	}

}
