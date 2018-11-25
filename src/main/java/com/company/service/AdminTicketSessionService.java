package com.company.service;

import com.company.entities.TicketEntity;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminTicketSessionService {
    public String addTicket(TicketEntity ticket) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "addTicket");
        object.put("ticket", ticket);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }

    public String updateTicket(TicketEntity ticket) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "updateTicket");
        object.put("ticket", ticket);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }

    public String deleteTicket(TicketEntity ticket) throws IOException, ClassNotFoundException{
        JSONObject object = new JSONObject();
        object.put("action", "deleteTicket");
        object.put("ticket", ticket);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
