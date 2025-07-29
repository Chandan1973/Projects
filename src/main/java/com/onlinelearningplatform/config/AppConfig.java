////package com.onlinelearningplatform.config;
////
////import java.security.cert.X509Certificate;
////import javax.net.ssl.SSLContext;
////import javax.net.ssl.TrustManager;
////import javax.net.ssl.X509TrustManager;
////import javax.net.ssl.HttpsURLConnection;
////import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
////import org.apache.hc.client5.http.impl.classic.HttpClients;
////import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
////import org.springframework.web.client.RestTemplate;
////
////@Configuration
////public class AppConfig {
////
////    @Bean
////    public RestTemplate restTemplate() throws Exception {
////        // Trust manager to bypass SSL verification
////        TrustManager[] trustAllCerts = new TrustManager[] {
////            new X509TrustManager() {
////                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
////                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
////                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
////            }
////        };
////
////        // Setup SSL context
////        SSLContext sslContext = SSLContext.getInstance("TLS");
////        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
////        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
////
////        // Create HTTP client that ignores hostname verification
////        CloseableHttpClient httpClient = HttpClients.custom()
////                .setSSLContext(sslContext)
////                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
////                .build();
////
////        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
////
////        return new RestTemplate(factory);
////    }
////}
////
////
//
//package com.onlinelearningplatform.config;
// 
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
//import org.apache.hc.core5.ssl.SSLContextBuilder;
//import org.apache.hc.core5.ssl.TrustStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
// 
//import javax.net.ssl.SSLContext;
//import java.security.cert.X509Certificate;
// 
//@Configuration
//public class AppConfig {
// 
//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        // Trust all certificates
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
// 
//        // Create SSL context using HttpClient 5 approach
//        SSLContext sslContext = SSLContextBuilder
//                .create()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
// 
//        // Build HttpClient with custom SSL context
//        CloseableHttpClient httpClient = (HttpClients.custom())
//                .setSSLContext(sslContext)
//                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                .build();
// 
//        // Create RestTemplate using this HttpClient
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
// 
//        return new RestTemplate(factory);
//    }
//}