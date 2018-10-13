package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static ServerSocket serverSocket;
    private Socket client;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static int numbClients;

    public Server(Socket socket){
        this.client = socket;
        numbClients++;
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run(){
        try{
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
