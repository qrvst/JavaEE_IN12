package com.mycompany.lab2_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecieveMessageFromServer implements Runnable {
    private InputStream inputStreamServer;

    public RecieveMessageFromServer(InputStream inputStreamServer) {
        this.inputStreamServer = inputStreamServer;
    }

    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage = null;
        
        while(true) {
            try{
                serverMessage = in.readLine();
                
                if(serverMessage != null){
                    if(serverMessage.equals("exit")){
                    try {
                        in.close();
                        inputStreamServer.close();
                        break;
                    
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                    System.out.print("\n" + serverMessage + "\nEnter message: ");
                }
            } catch(IOException ex){
                try {
                    in.close();
                    inputStreamServer.close();
                    break;
                    
                } catch (IOException ex1) {
                    throw new RuntimeException(ex1);
                }
            }
        }
    }    
}
