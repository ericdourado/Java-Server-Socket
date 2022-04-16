package com.mycompany.chat.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ericd
 */
public class ChatServer {

    public static final int PORT = 4000;
    private ServerSocket serverSocket;

    private void clientConnectionLoop() throws IOException {
        Socket ClientSocket = serverSocket.accept();
        System.out.println("O Cliente "
                + ClientSocket.getRemoteSocketAddress() + " conectou");
        Socket ClientSocket2 = serverSocket.accept();
        System.out.println("O Cliente "
                + ClientSocket2.getRemoteSocketAddress() + " conectou");
        while (true) {
            //MENSAGEM CLIENTE 1
            BufferedReader in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
            String msg = in.readLine();
            ClientSocket.getInputStream();
            System.out.println("Mensagem do Cliente: " + ClientSocket.getRemoteSocketAddress() + "  :" + msg);
            //MENSAGEM CLIENTE 2

            BufferedReader in2 = new BufferedReader(new InputStreamReader(ClientSocket2.getInputStream()));
            String msg2 = in2.readLine();
            ClientSocket2.getInputStream();
            System.out.println("Mensagem do Cliente: " + ClientSocket2.getRemoteSocketAddress() + "  :" + msg2);

        }
    }

    public void start() throws IOException {
        System.out.println("Iniciando o servidor....\n");
        serverSocket = new ServerSocket(PORT);
        clientConnectionLoop();
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o Servidor " + ex.getMessage());
        }
        System.out.println("\nO Servidor foi finalizado\n");
    }

}
