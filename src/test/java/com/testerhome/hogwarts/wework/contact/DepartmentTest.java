package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.Test;



class DepartmentTest {

    @Test
    void list() {
        Department department=new Department();
        department.list("").then().statusCode(200);
    }
}