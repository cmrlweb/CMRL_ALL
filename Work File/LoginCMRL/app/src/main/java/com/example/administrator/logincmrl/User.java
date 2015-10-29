package com.example.administrator.logincmrl;

/**
 * Created by administrator on 28/10/15.
 */
public class User {

    String username, password, name;
    int age;

    public User (String name, int age, String username, String password ) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public  User (String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "";
        this.age = -1;
    }

}
