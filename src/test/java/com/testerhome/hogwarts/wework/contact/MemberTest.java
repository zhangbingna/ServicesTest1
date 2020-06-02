package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    static Member member;
    @BeforeAll
    static void setup(){
        if(member==null) {
            member = new Member();
        }
    }

    @Test
    void getMember() {
        member.get("Anker");
    }

    @ParameterizedTest
    @ValueSource(strings ={"zhangbingna","bnazhang","LingTian"})
    void createByMap(String name){
        String namenew=name+member.random;
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0,5+8);
        HashMap<String,Object> map=new HashMap<String, Object>(){
            {
                put("userid",namenew);
                put("name",namenew);
                put("mobile","151"+random);
                put("department", Arrays.asList(1,2));
                put("email",random+"@163.com");
            }
        };
        member.create(map);
    }

    @Test
    void delete() {
        member.delete("bna");
    }
}