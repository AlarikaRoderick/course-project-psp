package com.company.server;

public class ServerMessage<T> {
    T object;
    String message;

    public ServerMessage(T object, String message) {
        this.object = object;
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
