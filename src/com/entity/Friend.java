package com.entity;

import com.delta.core.porter.annotation.Entity;
import com.delta.core.porter.annotation.Ignore;

@Entity("t_friend")
public class Friend {
    private int id;
    private int t_user_id;
    private String name;
    private boolean sex;
    private int age;
    private String qq;
    private String tel;
    private String email;
    private String address;

    @Ignore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(int t_user_id) {
        this.t_user_id = t_user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", t_user_id=" + t_user_id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", qq='" + qq + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
