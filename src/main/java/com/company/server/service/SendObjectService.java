package com.company.server.service;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SendObjectService {
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public SendObjectService(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public JSONObject getObject() throws IOException, ClassNotFoundException {
        return (JSONObject) this.objectInputStream.readObject();
    }

    public void sendObject(JSONObject object) throws IOException{
        this.objectOutputStream.writeObject(object);
    }

    public void sendMessage(String message) throws IOException{
        this.objectOutputStream.writeObject(message);
    }
}
