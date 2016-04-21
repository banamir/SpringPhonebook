package com.banamir.phonebook.model;



public class JsonMessage {

    private Long status;

    private Object data;

    public JsonMessage(Long status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Long getStatus() {
        return status;
    }


    public Object getData() {
        return data;
    }


}
