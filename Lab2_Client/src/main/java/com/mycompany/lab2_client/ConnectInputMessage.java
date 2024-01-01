package com.mycompany.lab2_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectInputMessage implements Runnable{
    private Socket serverConnect;
    private InputStream inputStreamServer;
    
    public ConnectInputMessage() throws IOException {
        try{
          serverConnect = new Socket("localhost", 8887);
          inputStreamServer = serverConnect.getInputStream();
        } catch(UnknownHostException ex) {
            serverConnect.close();
            inputStreamServer.close();
            throw new RuntimeException("Unknown host");
        } catch(IOException ex1){
            serverConnect.close();
            inputStreamServer.close();
            throw new RuntimeException("IO exception");
        }
    }
    
    public InputStream getInputStreamServer(){
        return inputStreamServer;
    }
    
    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage;
        
        while(true) {
            try{
                serverMessage = in.readLine();
                if(serverMessage != null){
                    System.out.println(serverMessage + '\n');
                    break;
                }
            } catch(IOException ex){
                try {
                    in.close();
                    serverConnect.close();
                    inputStreamServer.close();
                } catch (IOException ex1) {
                    throw new RuntimeException(ex);
                }
                
            }
        }
        
        PrintWriter out = null;
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        
        String userMessage = null;
        
        while(true) {
            System.out.print("Enter message: ");
            try{
                if(serverConnect.isClosed()){
                    try {
                        inputUser.close();
                        out.close();
                        inputStreamServer.close();
                        serverConnect.close();
                        break;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                userMessage = inputUser.readLine();
                out = new PrintWriter(serverConnect.getOutputStream(), true);
                out.println(userMessage);
            } catch(IOException e){
                try {
                    inputUser.close();
                    out.close();
                    inputStreamServer.close();
                    serverConnect.close();
                    break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
