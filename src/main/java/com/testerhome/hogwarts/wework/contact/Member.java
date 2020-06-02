package com.testerhome.hogwarts.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

public class Member extends Contact{
    public Response get(String userid){
        reset();
        Response response=requestSpecification
                .param("userid",userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all().extract().response();
        return response;
    }

    public Response create(HashMap<String,Object> map){
        reset();
        String body=template("/data/member.json",map);
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public Response delete(String userid){
        reset();
        return requestSpecification
                .param("userid",userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all().extract().response();
    }
}
