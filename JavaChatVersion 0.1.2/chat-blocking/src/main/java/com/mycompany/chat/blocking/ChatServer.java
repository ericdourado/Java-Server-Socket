package com.mycompany.chat.blocking;


import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author ericd
 */
public class ChatServer {

    private ServerSocket serverSocket;

    private void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    private Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }
    private Socket esperaConexao2() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    private void trataConexao(Socket socket, Socket socket2) throws IOException {
        try {

            //OBJETOS DE ENTRADA E SAÍDA
            
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            ObjectOutputStream output2 = new ObjectOutputStream(socket2.getOutputStream());
            ObjectInputStream input2 = new ObjectInputStream(socket2.getInputStream());
            String msg;
            String msg2 = null;
            while(true){
            
            //MENSAGENS DO CLIENTE 1
            //RECEBE MSG 
            msg = input.readUTF();
            System.out.println("Mensagem do Cliente: " + socket.getRemoteSocketAddress() + "  :" + msg);
                        
            //ENVIA MSG
            output2.writeUTF(msg);
            output2.flush();
             
            //MENSAGENS DO CLIENTE 2
            ////RECEBE MSG 
            msg2 = input2.readUTF();
            System.out.println("Mensagem do Cliente: " + socket2.getRemoteSocketAddress() + "  :" + msg2);

            //ENVIA MSG 
            output.writeUTF(msg2);
            output.flush();
            
            
            
           
            
           
            //fechar input e output antes de fechar servidor
            }

        } catch (IOException ex) {
            //Trata Conexão
            System.out.println("Problema no tratamento da conexão com o cliente " +
                    socket.getInetAddress());
            System.out.println("Erro: " + ex.getMessage());

        } 

    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.criarServerSocket(5555);
            System.out.println("..................Aguardando Conexão Cliente 1...............");
            Socket socket = server.esperaConexao();
            System.out.println("Cliente 1 Conectado...");
            System.out.println("..................Aguardando Conexão Cliente 2...............");
            Socket socket2 = server.esperaConexao2();
            System.out.println("Cliente 2 conectado....");
            System.out.println("Os dois cliente foram conectados...");
            System.out.println("Cliente " + socket.getRemoteSocketAddress() + " Conectado");
            System.out.println("Cliente " + socket2.getRemoteSocketAddress() + " Conectado");
            server.trataConexao(socket, socket2);
            

        } catch (IOException ex) {
            //TRATA ERRO
        }
    }
}
