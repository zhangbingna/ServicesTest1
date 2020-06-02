package com.testerhome.hogwarts.wework.contact;

import com.testerhome.hogwarts.wework.Resful;
import com.testerhome.hogwarts.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Contact extends Resful {
    String random=String.valueOf(System.currentTimeMillis());
    public Contact(){
        reset();
    }
    public void reset(){
        requestSpecification=given().log().all()
                .queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON);
    }
}
