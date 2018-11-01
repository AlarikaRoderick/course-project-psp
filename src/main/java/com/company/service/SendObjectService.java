package com.company.service;

import com.company.client.ClientConnection;
import org.json.simple.JSONObject;

import java.io.IOException;

public class SendObjectService {

    public static void sendObject(JSONObject object) throws IOException {
        ClientConnection.getObjectOutputStream().writeObject(object);
    }

    public static String getMessage() throws IOException, ClassNotFoundException{
        return (String) ClientConnection.getObjectInputStream().readObject();
    }
}
