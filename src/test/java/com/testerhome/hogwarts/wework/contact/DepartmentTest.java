package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;


class DepartmentTest {

    @Test
    void list() {
        Department department=new Department();
        department.list("").then().statusCode(200).body("department.name[2]",equalTo("测试部"));
        department.list("3").then().statusCode(200).body("department.name[0]",equalTo("测试部"));

    }
}