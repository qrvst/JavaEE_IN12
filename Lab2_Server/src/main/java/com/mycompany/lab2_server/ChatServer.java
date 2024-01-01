package com.mycompany.lab2_server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer implements Runnable{
    private Map<Integer, Socket> mapClient = new TreeMap<Integer, Socket>();
    PrintWriter out;
    
    @Override
    public void run() {
        try{
            ServerSocket server = new ServerSocket(8887);
            System.out.println("Server is started");
            int numberClient = 1;
            Socket client = null;
            
            while(true) {
                client = server.accept();
                Thread clientThread = new Thread(new ClientThread(client, this, numberClient));
                clientThread.setDaemon(true);
                clientThread.start();
                mapClient.put(numberClient, client);
                numberClient++;
            }
        } catch(IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public void sendMessageForAllClient(int numberClient, String clientMessage){
        
        if(clientMessage.equals("exit")){
            try {
                mapClient.get(numberClient).close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            mapClient.remove(numberClient);
            
            try {
                out = new PrintWriter(mapClient.get(numberClient).getOutputStream(), true); 
                out.println("exit");
            } catch (IOException ex) {
               throw new RuntimeException(ex);
            }
        } else {
            for(Map.Entry<Integer, Socket> entry : mapClient.entrySet()){
                if(entry.getKey() != numberClient){
                    try {
                        out = new PrintWriter(entry.getValue().getOutputStream(), true); 
                        out.println("Client №" + numberClient + ": " + clientMessage);
                    } catch (IOException ex) {
                        try {
                            entry.getValue().close();
                            mapClient.remove(entry.getKey());
                        } catch (IOException ex1) {
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex1);
                            throw new RuntimeException(ex1);
                        }
                    }
                }
            }
        }
    }
}
