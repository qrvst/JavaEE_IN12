package com.mycompany.lab2_server;

public class Lab2_Server {
    
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        Thread tChatServer = new Thread(chatServer);
        tChatServer.start();
    }
}
