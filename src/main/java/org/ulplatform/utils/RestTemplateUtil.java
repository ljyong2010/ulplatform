package org.ulplatform.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Administrator on 2017-7-13.
 */
public class RestTemplateUtil {
    private static RestTemplate restTemplate = new RestTemplate();
    public static String post(ServletRequest request, String url, Map<String,?> params){
        ResponseEntity<String> rss = request(request,url,HttpMethod.POST,params);
        return rss.getBody();
    }
    public static String get(ServletRequest request,String url,Map<String,?> params){
        ResponseEntity<String> rss = request(request,url,HttpMethod.GET,params);
        return rss.getBody();
    }
    public static String delete(ServletRequest request,String url,Map<String,?> params){
        ResponseEntity<String> rss = request(request,url,HttpMethod.DELETE,params);
        return rss.getBody();
    }
    public static String put(ServletRequest request,String url,Map<String,?> params){
        ResponseEntity<String> rss = request(request,url,HttpMethod.PUT,params);
        return rss.getBody();
    }
    public static ResponseEntity<String> request(ServletRequest servletRequest, String url, HttpMethod method,Map<String,?> params){
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        //header获取信息
        HttpHeaders httpHeaders = new HttpHeaders();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key=(String) headerNames.nextElement();
            String value=httpServletRequest.getHeader(key);
            httpHeaders.add(key,value);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(params!=null? JSONObject.toJSONString(params):null,httpHeaders);
        ResponseEntity<String> rss = restTemplate.exchange(url,method,requestEntity,String.class);
        return rss;
    }
}
