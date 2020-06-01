package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;


class DepartmentTest {

    Department department;
    @BeforeEach
    void setUP(){
        if (department==null){
            department=new Department();
        }
    }

    @Test
    void list() {

        //department.list("").then().statusCode(200).body("department.name[2]",equalTo("测试部"));
        department.list("3").then().statusCode(200).body("department.name[0]",equalTo("测试部"));

    }

    @Test
    void creat() {
        //department.creat();
        //department.creat("bnazhang03","2");
        department.creat("bnazhang03","2").
                then().body("errcode",equalTo(60010));
    }

    @Test
    void delet() {
        department.delet("6");
    }

    @Test
    void update() {
        department.update("8","bnazhang001");
    }
}