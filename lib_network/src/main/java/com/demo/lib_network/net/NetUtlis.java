package com.demo.lib_network.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetUtlis {

    public static String SendUrlRequest(String s) {
        try {
            URL url = new URL(s);//将传入的String对象解析为url（统一资源定位符）
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            //通过url的openConnection()方法打开一个网络连接对象并强转为HttpsUrlConnection对象
            //然后将httpsUrlConnection指向它
            httpsURLConnection.setReadTimeout(5000);
            //设置连接超时时间5000即5000毫秒=5秒钟
            httpsURLConnection.setRequestMethod("GET");
            //设置请求方法 GET
            InputStream inputStream = httpsURLConnection.getInputStream();
            //获得httpsUrlConnection的输入流对象
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //将输入流对象包装为字符缓冲输入流
            StringBuffer stringBuffer = new StringBuffer();
            //创建一个StringBuffer对象用于接收每次获取到的输入流信息,当然这里是间接通过下面的String对象来拼接
            String singleLineData = null;
            while ((singleLineData = bufferedReader.readLine()) != null) {
                //字符串singleLineData即每次从输入流中获取到的数据,当某一次获取为空时证明读取完毕,跳出循环
                stringBuffer.append(singleLineData);
                //不为空时,不断拼接stringBuffer
            }
            return stringBuffer.toString();
            //最后将调用toString()方法返回完整数据字符串
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
