/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat.blocking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ericd
 */
public class ChatClient2 {
    private static void MensagemLoop() throws IOException{
        Socket socket = new Socket("localhost", 5555);
        String msg = null;
        String msgreceptora = null;
        String aux = "";
        Scanner scanner = new Scanner(System.in);
        //Cria objetos de streams de inputs e outputs
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        System.out.print("Digite uma mensagem (ou 'sair' para finalizar):\n");
        System.out.println("Aperte enter para ativar o Chat");
        do{
            
            
            if (aux == ""){
            System.out.println("CLIENTE JOAO: ");
            msg = scanner.next();
            
            //envia mensagem pro servidor
            output.writeUTF(msg);
            output.flush();
            }
            
            //Recebe msg do servidor
            msgreceptora = input.readUTF();
            System.out.println("Mensagem do Eric: "  + msgreceptora);
           
        }while(!msg.equalsIgnoreCase("sair"));
        
    }
    
    public static void main(String[] args) {
        try {
            //Cria conexão do cliente e servidor
            MensagemLoop();

                    } catch (IOException ex) {
            //TRATAR PROBLEMAS
            
        }
    }
}
