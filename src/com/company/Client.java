package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 6023);) {
            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter request: ");
                String clientRequest = scanner.nextLine() + "\n";
                out.writeUTF(clientRequest);
                out.flush();
                System.out.println("Request sent.");
                if(clientRequest.equals("over\n"))  break;
                System.out.print("Server response: " + "\n" + in.readUTF() + "\n");
            }
            System.out.println("All messages sent.");
            scanner.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client closed.");
    }
}
