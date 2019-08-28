package com.example.shubham.pnbapp;

/**
 * Created by prashant maheshwari on 22-07-2018.
 */

public class CreateRequest {
    private String To,From1,From,Reason;

    public CreateRequest() {
    }

    public CreateRequest(String from1, String to, String reason, String from) {
        To = to;
        From1 = from1;
        From = from;
        Reason = reason;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getFrom1() {
        return From1;
    }

    public void setFrom1(String from1) {
        From1 = from1;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}