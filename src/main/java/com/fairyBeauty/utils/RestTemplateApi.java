package com.fairyBeauty.utils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class RestTemplateApi {

    public static RestTemplate restTemplate = new RestTemplate();
    public static String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //60s
        requestFactory.setConnectTimeout(25000);
        requestFactory.setReadTimeout(25000);
        restTemplate.setRequestFactory(requestFactory);
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss

        objectMapper.setDateFormat(new SimpleDateFormat(DATE_PATTERN));
        jsonMessageConverter.setObjectMapper(objectMapper);

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (int i = 0; i < messageConverters.size(); i++) {
            HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
            if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
                messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            } else if (httpMessageConverter.getClass().equals(MappingJackson2HttpMessageConverter.class)) {
                messageConverters.set(i, jsonMessageConverter);
            }
        }
    }


    public static <T> T post(String url, Object query, ParameterizedTypeReference<T> responseType) {
        return exchange(url, HttpMethod.POST, null, query, responseType);
    }

    public static <T> T post(String url, Map<String, String> header, Object query, ParameterizedTypeReference<T> responseType) {
        return exchange(url, HttpMethod.POST, header, query, responseType);
    }

    public static <T> T get(String url, Map<String, String> header, Object query, ParameterizedTypeReference<T> responseType) {
        return exchange(url, HttpMethod.GET, header, query, responseType);
    }
    public static <T> T request(String url, Map<String, String> header, HttpMethod httpMethod, Object query, ParameterizedTypeReference<T> responseType) {
        return exchange(url, httpMethod, header, query, responseType);
    }

    public static <T> T exchange(String url, HttpMethod httpMethod, Map<String, String> header, Object query, ParameterizedTypeReference<T> responseType) {

        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (header != null) {
            headers.setAll(header);
        }

        //设置访问的Entity
        HttpEntity entity = new HttpEntity<>(query, headers);

        ResponseEntity<T> exchange = restTemplate.exchange(URI.create(url), httpMethod, entity, responseType);
        return exchange.getBody();
    }

}
