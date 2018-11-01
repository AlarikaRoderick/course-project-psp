package com.company.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
    private static Socket socket;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    public static void createConnection() throws IOException {
        socket = new Socket("127.0.0.1", 1111);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        ClientConnection.socket = socket;
    }

    public static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public static void setObjectInputStream(ObjectInputStream objectInputStream) {
        ClientConnection.objectInputStream = objectInputStream;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public static void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        ClientConnection.objectOutputStream = objectOutputStream;
    }
}
