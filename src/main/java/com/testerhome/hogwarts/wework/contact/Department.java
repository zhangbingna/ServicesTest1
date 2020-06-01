package com.testerhome.hogwarts.wework.contact;

import com.jayway.jsonpath.JsonPath;
import com.testerhome.hogwarts.wework.Wework;
import com.testerhome.hogwarts.wework.WeworkConfig;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class Department {
    public Response list(String id){
        return given().log().all()
                .param("access_token", Wework.getToken())
                .param("id",id)
        .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then().log().all().statusCode(200).extract().response();
    }
    public Response creat(String name,String parentid){
       String body=JsonPath.parse(this.getClass().getResourceAsStream("/data/creat.json"))
               .set("$.name",name)
               .set("$.parentid",parentid)
               .jsonString();
       return given().log().all()
               .queryParam("access_token",Wework.getToken())
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();
    }
    public Response delet(String id){
        return given()
                .param("access_token",Wework.getToken())
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all().statusCode(200).extract().response();
    }
    public Response update(String id,String name){
        String body=JsonPath.parse(this.getClass().getResourceAsStream("/data/creat.json"))
                .set("$.id",id)
                .set("$.name",name)
                .jsonString();
        return given().log().all().queryParam("access_token",Wework.getToken())
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().statusCode(200).extract().response();
    }
}
