package com.dubboclub.dk.commons.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Https请求工具类
 * 
 * @author 杨丽[yanglizd@chinamobile.com]
 * @date 2017年4月14日 - 下午5:28:19
 * @history 2017年4月14日 - 下午5:28:19 杨丽[yanglizd@chinamobile.com] create.
 */
public class HttpsUtils {
	private static Logger log = LoggerFactory.getLogger(HttpsUtils.class.getName());

	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;

	static {
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(),
					new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register(HTTP, PlainConnectionSocketFactory.getSocketFactory()).register(HTTPS, sslsf).build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(200);// max connection
		} catch (Exception e) {
			log.error("！！！SSL连接失败");
		}
	}

	/**
	 * 建立连接
	 * 
	 * @author Alice
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpClient getHttpClient() throws Exception {
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000)
				.setSocketTimeout(2000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm)
				.setDefaultRequestConfig(config).setConnectionManagerShared(true).build();
		return httpClient;
	}

	/**
	 * httpClient post请求
	 * 
	 * @param url
	 *            请求url
	 * @param header
	 *            头部信息
	 * @param param
	 *            请求参数 form提交适用
	 * @param entity
	 *            请求实体 json/xml提交适用
	 * @return 可能为空 需要处理
	 * @throws Exception
	 *
	 */
	public static Map<String, Object> post(String url, Map<String, String> header, String param, HttpEntity entity)
			throws Exception {
		CloseableHttpClient httpClient = null;

		httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		// 设置头信息
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		if (!StringUtils.isEmpty(param)) {
			StringEntity stringEntity = new StringEntity(param, "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
		}

		// 设置实体 优先级高
		if (entity != null) {
			httpPost.setEntity(entity);
		}
		return executeHttp(httpClient, httpPost);
	}

	/**
	 * post
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 * @author 杨丽[yanglizd@chinamobile.com]
	 * @date 2017年4月15日 - 下午2:22:09
	 * @history 2017年4月15日 - 下午2:22:09 杨丽[yanglizd@chinamobile.com] create.
	 */
	public static Map<String, Object> post(String url, String params) throws Exception {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		Map<String, Object> result = post(url, header, params, null);
		return result;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param header
	 * @param params
	 * @return
	 * @author 杨丽[yanglizd@chinamobile.com]
	 * @date 2017年4月14日 - 下午5:28:43
	 * @history 2017年4月14日 - 下午5:28:43 杨丽[yanglizd@chinamobile.com] create.
	 */
	public static Map<String, Object> get(String url, Map<String, String> header, Map<String, String> params) {
		String apiUrl = url;
		CloseableHttpClient httpClient = null;

		try {
			httpClient = getHttpClient();
		} catch (Exception e) {
			log.error("！！！https创建失败", e);
		}

		// 设置请求参数
		if (params != null && params.size() > 0) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (Entry<String, String> entry : params.entrySet()) {
				if (i == 0) {
					param.append("?");
				} else {
					param.append("&");
				}
				param.append(entry.getKey()).append("=").append(entry.getValue());
				i++;
			}
			apiUrl += param;
		}

		HttpGet httpGet = new HttpGet(apiUrl);
		// 加载并设置所有请求头信息
		if (header != null && header.size() > 0) {
			Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				httpGet.setHeader(entry.getKey(), entry.getValue());
			}
		}

		return executeHttp(httpClient, httpGet);
	}

	/**
	 * 
	 * @param httpResponse
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @author 杨丽[yanglizd@chinamobile.com]
	 * @date 2017年4月14日 - 下午5:29:02
	 * @history 2017年4月14日 - 下午5:29:02 杨丽[yanglizd@chinamobile.com] create.
	 */
	public static String readHttpResponse(HttpResponse httpResponse) throws ParseException, IOException {
		StringBuilder builder = new StringBuilder();
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		builder.append("status:" + httpResponse.getStatusLine());
		builder.append("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			builder.append("\t" + iterator.next());
		}
		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			builder.append("response length:" + responseString.length());
			builder.append("response content:" + responseString.replace("\r\n", ""));
		}
		return builder.toString();
	}

	/**
	 * 执行https请求
	 * 
	 * @param httpClient
	 * @param request
	 * @return
	 * @author 杨丽[yanglizd@chinamobile.com]
	 * @date 2017年4月14日 - 下午5:29:07
	 * @history 2017年4月14日 - 下午5:29:07 杨丽[yanglizd@chinamobile.com] create.
	 */
	private static Map<String, Object> executeHttp(CloseableHttpClient httpClient, HttpRequestBase request) {
		Map<String, Object> results = new HashMap<String, Object>();
		String url = "";
		url = request.getURI().toString();
		log.debug("requestUrl={}", url);
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			log.info("statusCode:{}", statusCode);
			results.put("httpStts", statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = httpResponse.getEntity();
				String httpStr = EntityUtils.toString(resEntity, "UTF-8");
				results.put("responseData", httpStr);
			} else {
				results.put("responseData", readHttpResponse(httpResponse));
			}
		} catch (Exception e) {
			log.error("！！！https请求客户端协议错误, requestUrl={}", url, e);
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {

					log.error("！！！https客户端关闭失败", e);
				}
			}
		}
		return results;
	}
}
