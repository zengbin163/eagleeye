package com.dubboclub.dk.commons.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

// TODO Auto-generated Javadoc
/**
 * The Class HttpUtils.
 */
public class HttpUtils {

	/** The url. */
	public static URL url;

	/** The http URL conn. */
	public static HttpURLConnection httpURLConn;

	/**
	 * Url decode.
	 *
	 * @author houyuliang
	 * @param data
	 *            the data
	 * @return decodeData
	 * @Description URL参数解码
	 * @time 2015年6月26日 上午10:12:03
	 */
	public static String urlDecode(String data) {
		String decodeData = null;
		try {
			decodeData = URLDecoder.decode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodeData;
	}

	/**
	 * Url encode.
	 *
	 * @author houyuliang
	 * @param data
	 *            the data
	 * @return encodeData
	 * @Description URL参数转码
	 * @time 2015年6月26日 上午10:12:14
	 */
	public static String urlEncode(String data) {
		String encodeData = null;
		try {
			encodeData = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeData;
	}

	/**
	 * 得到请求的根目录
	 * 
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath;
	}

	/**
	 * 获取服务器的路径(tomcat/webapp)
	 * 
	 * @param request
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		return basePath;
	}

	/**
	 * 发送消息
	 * 
	 * @author Alice
	 * @param powerKey
	 * @param timestamp
	 * @param signature
	 * @param postUrl
	 * @param param
	 * @return
	 */
	public static String pushDataToThirdPlatform(Map<String, String> header, String postUrl, String param) {
		String resStr = null;
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpClientBuilder.build();
		HttpPost httppost = new HttpPost(postUrl.toString());
		StringEntity stringEntity;
		try {
			// 设置头信息
			if (header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httppost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			stringEntity = new StringEntity(param, "UTF-8");
			stringEntity.setContentType("application/json");
			stringEntity.setContentEncoding("UTF-8");
			httppost.setEntity(stringEntity);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
			httppost.setConfig(requestConfig);
			System.out.println(httppost.toString());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity resEntity = response.getEntity();
				resStr = EntityUtils.toString(resEntity, "UTF-8");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resStr;
	}

	public static void main(String[] arg) {
		pushDataToThirdPlatform(null, "https://218.205.115.242:10443/open/msgFlow/push", "param");
	}
}
