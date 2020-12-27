package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket welcomingSocket = new ServerSocket(6023);) {
            System.out.println("Waiting for a client...");

            for (int i = 1; true; i++) {
                Socket connectionSocket = welcomingSocket.accept();
                System.out.println("Client" + i + " accepted.");

                Thread clientThread = new Thread(new ClientHandler(connectionSocket, i));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
