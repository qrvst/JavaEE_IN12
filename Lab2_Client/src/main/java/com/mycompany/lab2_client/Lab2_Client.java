package com.mycompany.lab2_client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab2_Client {

    public static void main(String[] args) {
        ConnectInputMessage connectWithServer;
        try {
            connectWithServer = new ConnectInputMessage();
        } catch (IOException ex) {
             throw new RuntimeException(ex);
        }
        Thread tConnectInputMessage = new Thread(connectWithServer);
        tConnectInputMessage.start();
        
        RecieveMessageFromServer recieveMessage = new RecieveMessageFromServer(connectWithServer.getInputStreamServer());
        Thread tRecieveMessage = new Thread(recieveMessage);
        tRecieveMessage.start();
    }
}
