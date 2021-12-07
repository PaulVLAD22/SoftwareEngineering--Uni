package com.university.ilock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailUserModel {

    @JsonProperty("name")
    private String Name;
    @JsonProperty("email")
    private String Email;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
