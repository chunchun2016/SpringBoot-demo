package com.example.domain;

import java.util.Date;

/**
 * Created by admin on 17/4/24.
 */

public class SysUser implements java.io.Serializable{
    private int id;
    private String name;
    private String password;//用户密码
    private Integer age;
    private String email;//用户邮箱
    private Date dob;//时间

    public SysUser() {
    }

    public SysUser(String name, String password, Integer age, String email, Date dob) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}
