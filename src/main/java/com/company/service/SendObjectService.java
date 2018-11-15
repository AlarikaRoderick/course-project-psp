package com.company.service;

import com.company.client.ClientConnection;
import org.json.simple.JSONObject;

import java.io.IOException;

public class SendObjectService {

    public static void sendObject(JSONObject object) throws IOException {
        ClientConnection.getObjectOutputStream().writeObject(object);
    }

    public static void sendMessage(String message) throws IOException{
        ClientConnection.getObjectOutputStream().writeObject(message);
    }

    public static String getMessage() throws IOException, ClassNotFoundException{
        return (String) ClientConnection.getObjectInputStream().readObject();
    }

    public static JSONObject getObject() throws IOException, ClassNotFoundException{
        return (JSONObject) ClientConnection.getObjectInputStream().readObject();
    }
}
