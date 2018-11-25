package com.company.server;

import com.company.dbHandler.DbHandler;
import com.company.server.service.ThreadService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException{
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        ServerSocket serverSocket = new ServerSocket(1111);
        System.out.println("server is ready ");
        while(true){
            Socket socket = serverSocket.accept();
            ThreadService threadService = new ThreadService(socket);
            threadService.start();
        }
    }
}
