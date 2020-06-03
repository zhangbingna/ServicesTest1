package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;


class DepartmentTest {

    Department department;
    String random=String.valueOf(System.currentTimeMillis());
    @BeforeEach
    void setUP(){
        if (department==null){
            department=new Department();
        }
        department.deleteAll();
    }

    @Test
    void list() {

        //department.list("").then().statusCode(200).body("department.name[2]",equalTo("测试部"));
        department.list("");

    }

    @Test
    void create() {
        department.create("张冰娜001","1").then().body("errcode",equalTo(0));
    }


    @Test
    void delete() {
        String name="bnazhang02"+random;
        Integer idInt=department.create(name,"1").path("id");
        //Integer idInt=department.list("").path("department.find{it.name=='"+name+"'}.id");
        System.out.println(idInt);
        String id=String.valueOf(idInt);
        department.delete(id).then().body("errcode",equalTo(0));
    }

    @Test
    void update() {
        String name="bnazhang02"+random;
        Integer idInt=department.create(name,"1").path("id");
        //Integer idInt=department.list("").path("department.find{it.name=='"+name+"'}.id");
        String id=String.valueOf(idInt);
        department.update(id,"bnazhang3"+random).then().body("errcode",equalTo(0));
    }

    @Test
    void createByMap() {
        HashMap<String,Object> map=new HashMap<String, Object>(){
            {
                put("name","zhangbingna0001+map"+random);
                put("parentid","1");
            }
        };
        department.create(map).then().body("errcode",equalTo(0));
    }
}