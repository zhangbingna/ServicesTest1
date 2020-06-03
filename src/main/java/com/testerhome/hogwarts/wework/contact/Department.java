package com.testerhome.hogwarts.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;


import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;




public class Department extends Contact{
    public Response list(String id){
        reset();
        Response response=requestSpecification
                .param("id",id)
        .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then().log().all().extract().response();
        return response;
    }
    public Response create(String name,String parentid){
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
    public Response create(HashMap<String,Object> map){
        reset();
        DocumentContext documentContext=JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/creat.json"));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(),entry.getValue());
        });
        return requestSpecification
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

    public Response delete(String id){
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
    public void deleteAll(){
        reset();
        List<Integer> idlist=list("").
                then().extract().path("department.id");
        System.out.println(idlist);
        idlist.forEach(id->delete(id.toString()));
    }

    public void createFromHar(HashMap<String,Object> map){
        reset();

        templateFromHar(
                "/data/demo.har.json",
                "https://work.weixin.qq.com/wework_admin/party?action=addparty",
                map
        );
    }
    public void apiForAll(HashMap<String,Object> map){
        api("/data/demo.har.json","addparty",map);
    }
}
