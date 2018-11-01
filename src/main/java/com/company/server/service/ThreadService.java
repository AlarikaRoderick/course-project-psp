package com.company.server.service;

import com.company.server.ServerWorker;

import java.io.IOException;
import java.net.Socket;

public class ThreadService extends Thread {

    private ServerConnectionService serverConnectionService;
    private SendObjectService sendObjectService;

    public ThreadService(Socket socket) throws IOException {
        this.serverConnectionService = new ServerConnectionService();
        this.serverConnectionService.createConnection(socket);
        this.sendObjectService = new SendObjectService(this.serverConnectionService.getObjectInputStream(),
                this.serverConnectionService.getObjectOutputStream());
    }

    @Override
    public void run() {
        try{
            System.out.println("Thread start");
            ServerWorker serverWorker = new ServerWorker(sendObjectService);
            serverWorker.runServerWorker();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void disconnect(){
        try{
            serverConnectionService.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
