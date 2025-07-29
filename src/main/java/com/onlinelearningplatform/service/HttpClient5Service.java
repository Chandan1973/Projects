////package com.onlinelearningplatform.service;
//// 
////import org.apache.hc.client5.http.classic.methods.HttpGet;
////import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
////import org.apache.hc.client5.http.impl.classic.HttpClients;
////import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
////import org.apache.hc.core5.ssl.SSLContextBuilder;
////import org.apache.hc.core5.ssl.TrustStrategy;
////import org.apache.hc.core5.http.ClassicHttpResponse;
////import org.apache.hc.core5.http.io.entity.EntityUtils;
////import org.springframework.stereotype.Service;
//// 
////import javax.net.ssl.SSLContext;
////import java.security.cert.X509Certificate;
//// 
////@Service
////public class HttpClient5Service {
//// 
////    public String fetchFromApi(String url) throws Exception {
////        // Trust all certificates
////        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//// 
////        // Create SSL context that accepts all certs
////        SSLContext sslContext = SSLContextBuilder.create()
////                .loadTrustMaterial(null, acceptingTrustStrategy)
////                .build();
//// 
////        // Build HTTP client with custom SSL context
////        try (CloseableHttpClient httpClient = HttpClients.custom()
////                .setSSLContext()
////                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
////                .build()) {
//// 
////            HttpGet request = new HttpGet(url);
////            try (ClassicHttpResponse response = httpClient.executeOpen(null, request, null)) {
////                return EntityUtils.toString(response.getEntity());
////            }
////        }
////    }
////}
//
//
//
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.classic.HttpClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
//import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
//import org.apache.hc.client5.http.ssl.TrustAllStrategy;
//import org.apache.hc.core5.ssl.SSLContextBuilder;
//import org.apache.hc.core5.http.ClassicHttpRequest;
//import org.apache.hc.core5.http.io.entity.EntityUtils;
// 
//import javax.net.ssl.SSLContext;
//import java.security.cert.X509Certificate;
// 
//public class HttpClient5Service {
// 
//    public String fetchFromApi(String url) throws Exception {
//        // Trust all certificates
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
//                .build();
// 
//        // Create SSL socket factory
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
//                sslContext, NoopHostnameVerifier.INSTANCE);
// 
//        // Build HTTP client with custom SSL socket factory
//        try (CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(socketFactory)
//                .build()) {
// 
//            HttpGet request = new HttpGet(url);
// 
//            try (ClassicHttpRequests response = httpClient.executeOpen(null, request, null)) {
//                return EntityUtils.toString(response.getEntity());
//            }
//        }
//    }
//}