package com.testerhome.hogwarts.wework;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class Resful {
    HashMap<String,Object> query=new HashMap<String, Object>();
    public RequestSpecification requestSpecification=given();
    public Response send(){
        requestSpecification=given().log().all();
        query.entrySet().forEach( entry-> {
            requestSpecification.queryParam(entry.getKey(),entry.getValue());
        } );
        return requestSpecification.when().request("get","https://qyapi.weixin.qq.com/cgi-bin");
    }
    public static String template(String path,HashMap<String,Object> map){
        DocumentContext documentContext= JsonPath.parse(Resful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(),entry.getValue());
        });
        return documentContext.jsonString();
    }
    public Response templateFromHar(String path,String pattern,HashMap<String,Object> map){
        //todo:支持从har文件读取接口定义并发送
        //HTTP发送引擎
        DocumentContext documentContext= JsonPath.parse(Resful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(),entry.getValue());
        });
        String method=documentContext.read("method");
        String url=documentContext.read("url");

        return requestSpecification.when().request(method,url);
    }
    public Response templateFromSwagger(String path,String pattern,HashMap<String,Object> map) {
        //todo:支持从swagger文件读取接口定义并发送
        DocumentContext documentContext = JsonPath.parse(Resful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        String method = documentContext.read("method");
        String url = documentContext.read("url");

        return requestSpecification.when().request(method, url);
    }
    public Response api(String path,String pattern,HashMap<String,Object> map){
        //todo:动态调用
        return requestSpecification.when().request("");
    }
}
