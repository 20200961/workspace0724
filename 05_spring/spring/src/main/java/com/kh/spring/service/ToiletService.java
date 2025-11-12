package com.kh.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ToiletService {

    @Value("${seoul.api.key}")
    private String apiKey;

    private static final String API_URL = "http://openapi.seoul.go.kr:8088";   // 1개 사용 위치

    public void getToiletList(int start, int end) {    // 0개의 사용 위치  신규 *

        // http://openapi.seoul.go.kr:8088/인증키/응답형식/mgisToiletPoi/시작인덱스/끝인덱스/
        String url = String.format("%s/%s/json/mgisToiletPoi/%d/%d", API_URL,apiKey,start,end);
        

        RestTemplate restTemplate = new RestTemplate();
    }
}
