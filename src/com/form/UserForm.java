package com.form;

import com.delta.core.rover.XForm;

public class UserForm implements XForm {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean validate() {
        if (username == null || username.equals("") || username.contains(" ") || username.contains("<")) {
            return false;
        }
        if (password == null || password.equals("") || password.contains(" ") || username.contains("<")) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
