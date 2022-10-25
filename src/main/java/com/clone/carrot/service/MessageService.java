package com.clone.carrot.service;

import java.util.HashMap;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    String api_key = "NCSVDZASPR8SENN8";
    String api_secret = "FT2DMIXTRTKU2EFGMH4RPI9WVSIBVSGX";
    String fromNumber="01092689162";

    public void sendMessage(String toNumber, String randomNumber) {

        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[당근마켓] 인증번호 "+randomNumber+" 를 입력하세요.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

    //6자리의 난수 생성


}

