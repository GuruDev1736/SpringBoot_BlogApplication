package com.Guruprasad.Blog.PayLoad;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp ;
    private String message;
    private String details ;

    public ErrorDetails( Date timestamp, String message, String details) {

        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
