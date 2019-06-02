package com.pal.thirstymission.interntask;

import java.sql.Timestamp;

public class User {
private String name;
private int age;
private Timestamp timestamp;

public User()
{

}
    public User(String name, int age,Timestamp timestamp){
        this.name=name;
        this.age=age;
        this.timestamp=timestamp;
    }


        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}








