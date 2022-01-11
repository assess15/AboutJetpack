package com.demo.lib_network.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @Author Liudeli
 * @Describe：Java版(HttpURLConnection)Https请求工具类
 */
public class HttpsURLConnectionHelp {

    /**
     * 加密协议
     */
    public static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    public static String requestByGetToString(String url, String jsessionid) {
        String message = null;
        try {
            HttpsURLConnection conn = getHttpsConnection(url);

            conn.setRequestProperty("cookie", jsessionid);
            // 请求成功（相应码 == 200）
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 取得该连接的输入流，以读取响应内容
                InputStreamReader insr = new InputStreamReader(
                        conn.getInputStream());
                StringBuffer strb = new StringBuffer();
                int s;
                while ((s = insr.read()) != -1) {
                    strb.append((char) s);
                }
                message = strb.toString();
                Log.i("", message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static InputStream requestByGetToStream(String url) {
        InputStream inputStream = null;
        try {
            HttpsURLConnection conn = getHttpsConnection(url);
            //自动重定向新地址
            conn.setInstanceFollowRedirects(true);
            // 请求成功（相应码 == 200）
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 取得该连接的输入流，以读取响应内容
                inputStream = conn.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static String requestByGetToString(String url) {
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            HttpsURLConnection conn = getHttpsConnection(url);
            //自动重定向新地址
            conn.setInstanceFollowRedirects(true);
            // 请求成功（相应码 == 200）
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 取得该连接的输入流，以读取响应内容
                inputStream = conn.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
                String data = null;
                while ((data = bf.readLine()) != null) {
                    sb.append(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static Object[] requestByGetToBitmap(String url) {
        Object[] objects = null;
        try {
            HttpsURLConnection conn = getHttpsConnection(url);
            //自动重定向新地址
            conn.setInstanceFollowRedirects(true);
            // 请求成功（相应码 == 200）
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                objects = new Object[2];
                String cookieval = conn.getHeaderField("set-cookie");
                String jsessionid = cookieval.substring(0, cookieval.indexOf(";"));

                InputStream is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                objects[0] = jsessionid;
                objects[1] = bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public String requestByGetToParams(String urlStr, Map<String, String> params, String jsessionid) {
        String responseContent = null;
        StringBuilder sb = new StringBuilder(urlStr);
        sb.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            //防止中文乱码
            sb.append(entry.getValue());
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        try {
            HttpsURLConnection conn = getHttpsConnection(urlStr);

            conn.setRequestProperty("cookie", jsessionid);

            //请求成功（相应码 == 200）
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                int s;
                StringBuffer strb = new StringBuffer();
                while ((s = is.read()) != -1) {
                    strb.append((char) s);
                }
                responseContent = strb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static HttpsURLConnection getHttpsConnection(String urlStr) {
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        HttpsURLConnection conn = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{truseAllManager}, null);
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL myURL = new URL(urlStr);

            conn = (HttpsURLConnection) myURL.openConnection();
            //设置加密协议
            conn.setSSLSocketFactory(ssf);
            //设置请求方式
            conn.setRequestMethod("GET");
            //设置连接超时时长
            conn.setConnectTimeout(15000);

            conn.setReadTimeout(15000);

            conn.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}