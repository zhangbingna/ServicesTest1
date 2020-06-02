package com.testerhome.hogwarts.wework.contact;

import com.testerhome.hogwarts.wework.Resful;
import com.testerhome.hogwarts.wework.Wework;

import static io.restassured.RestAssured.given;

public class Contact extends Resful {
    public Contact(){
        reset();
    }
    public void reset(){
        requestSpecification.log().all()
                .param("access_token", Wework.getToken())
                .expect().log().all().statusCode(200);
    }
}
