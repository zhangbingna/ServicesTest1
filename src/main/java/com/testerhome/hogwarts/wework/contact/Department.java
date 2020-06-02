package com.testerhome.hogwarts.wework.contact;

import com.jayway.jsonpath.JsonPath;
import com.testerhome.hogwarts.wework.Wework;
import com.testerhome.hogwarts.wework.WeworkConfig;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;


public class Department extends Contact{
    public Response list(String id){
        reset();
        Response response=requestSpecification
                .param("id",id)
        .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then().log().all().extract().response();
        return response;
    }
    public Response creat(String name,String parentid){
        reset();
       String body=JsonPath.parse(this.getClass().getResourceAsStream("/data/creat.json"))
               .set("$.name",name)
               .set("$.parentid",parentid)
               .jsonString();
       return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();

    }
    public Response delet(String id){
        reset();
        return requestSpecification
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all().extract().response();
    }
    public Response update(String id,String name){
        reset();
        String body=JsonPath.parse(this.getClass().getResourceAsStream("/data/creat.json"))
                .set("$.id",id)
                .set("$.name",name)
                .jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().extract().response();
    }
}
