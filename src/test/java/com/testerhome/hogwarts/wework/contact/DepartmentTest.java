package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
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
    }

    @Test
    void list() {

        //department.list("").then().statusCode(200).body("department.name[2]",equalTo("测试部"));
        department.list("");

    }

    @Test
    void creat() {
        //department.creat();
        //department.creat("bnazhang03","2");
        //department.creat("bnazhang03","2");
                //.then().body("errcode",equalTo(60010));
        department.creat("张冰娜001","2");
    }


    @Test
    void delet() {
        String name="bnazhang02"+random;
        Integer idInt=department.creat(name,"2").path("id");
        //Integer idInt=department.list("").path("department.find{it.name=='"+name+"'}.id");
        System.out.println(idInt);
        String id=String.valueOf(idInt);
        department.delet(id);
    }

    @Test
    void update() {
        String name="bnazhang02"+random;
        Integer idInt=department.creat(name,"2").path("id");
        //Integer idInt=department.list("").path("department.find{it.name=='"+name+"'}.id");
        String id=String.valueOf(idInt);
        department.update(id,"bnazhang3"+random);
        department.delet(id);
    }

    @Test
    void createByMap() {
        HashMap<String,Object> map=new HashMap<String, Object>(){
            {
                put("name","zhangbingna0001+map"+random);
                put("parentid","2");
            }
        };
        Integer idInt=department.create(map).path("id");
        String id=String.valueOf(idInt);
        department.delet(id);
    }
}