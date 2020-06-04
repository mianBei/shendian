package com.example.common.util;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class SSLUtils {
    private static Logger log = LoggerFactory.getLogger(SSLUtils.class);
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory ssl = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(ssl).build();
        } catch (KeyManagementException e) {
            log.error("SSLUtilsErrorKetManage");
        } catch (NoSuchAlgorithmException e) {
            log.error("SSLUtilsErrorNOAlgorithm");
        } catch (KeyStoreException e) {
            log.error("SSLUtilsErrorKeyStore");
        }
        return HttpClients.createDefault();
    }
}
