package com.fairyBeauty.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpsUtils.class);
    static CloseableHttpClient httpClient;
    static CloseableHttpResponse httpResponse;

    public static RestTemplate restTemplate = new RestTemplate();
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();

    }

    /**
     * 发送https请求
     *
     * @param jsonPara
     * @throws Exception
     */
    public static String  sendByHttp(Object params, String url) {
        try {
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity1 = new StringEntity(JSON.toJSONString(params), "UTF-8");
            logger.info("创建请求httpPost-URL={},params={}", url, params);
            httpPost.setEntity(httpEntity1);
            //请求中加入信任证书
            httpClient = HttpsUtils.createSSLClientDefault();
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, "UTF-8");
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
                logger.info("请求流关闭完成");
            } catch (IOException e) {
                logger.info("请求流关闭出错");
                e.printStackTrace();
            }
        }
    }
}
