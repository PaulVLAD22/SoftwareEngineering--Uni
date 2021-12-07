package com.university.ilock.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class EmailModel {

    private MailUserModel Sender;

    private ArrayList<MailUserModel> Recipients;

    private String Subject;

    private String Content;

    @JsonProperty("sender")
    public MailUserModel getSender() {
        return Sender;
    }

    public void setSender(MailUserModel sender) {
        Sender = sender;
    }

    @JsonProperty("to")
    public ArrayList<MailUserModel> getRecipients() {
        return Recipients;
    }

    public void setRecipients(ArrayList<MailUserModel> recipients) {
        Recipients = recipients;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @JsonProperty("htmlContent")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
