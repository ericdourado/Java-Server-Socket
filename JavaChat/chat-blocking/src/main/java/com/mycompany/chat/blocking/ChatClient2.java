/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ericd
 */
public class ChatClient2 {
    private final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter out;
    
    public ChatClient2(){
        scanner = new Scanner(System.in);
    }
    
    public void start() throws IOException{
        clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
     
        System.out.println("Cliente conectado ao servidor" + SERVER_ADDRESS);
        messageLoop();
    }
    
    private void messageLoop() throws IOException{
        String msg;
        do{
            System.out.print("Digite uma mensagem (ou 'sair' para finalizar):");
            msg = scanner.nextLine(); 
            out.println(msg);
        }while(!msg.equalsIgnoreCase("sair"));
    }
    
    public static void main(String[] args) {    
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar cliente..." + ex.getMessage());
            
        }System.out.println("CLIENTE FINALIZADO!");
    }
}
