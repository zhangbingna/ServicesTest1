package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        String id=String.valueOf(department.list("").path("department.find{it.name=='"+name+"'}.id"));
        department.creat(name,"2");
        department.delet(id);
    }

    @Test
    void update() {
        String name="bnazhang02"+random;
        department.creat(name,"2");
        String id=String.valueOf(department.list("").path("department.find{it.name=='"+name+"'}.id"));
        department.update(id,"bnazhang3"+random);
        department.delet(id);
    }
}