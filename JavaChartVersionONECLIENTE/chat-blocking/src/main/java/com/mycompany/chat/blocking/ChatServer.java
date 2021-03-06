package com.mycompany.chat.blocking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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

    private void trataConexao(Socket socket) throws IOException {

        try {

            //OBJETOS DE ENTRADA E SAÍDA
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            //Salvar em arquivo texto
            ReaderAndWriter escritaLeitura = new ReaderAndWriter();
            //Scanner
            Scanner scanner = new Scanner(System.in);
            //Criar arquivo texto
            String arq = "Arquivo.txt";
            while (true) {

                //MENSAGENS DO CLIENTE 
                //RECEBE MSG 
                String receptor = input.readUTF();
                System.out.println("Mensagem do Cliente: " + socket.getRemoteSocketAddress() + "  :" + receptor);

                //DIGITAR MSG PARA ENVIAR PRO SERVIDOR
                System.out.print("Digite: ");
                String msg = scanner.next();

                //ENVIA MSG 
                output.writeUTF(msg);
                output.flush();

                //ESCREVER MSG
                escritaLeitura.Write(arq, "Mensagem do Cliente:" + socket.getRemoteSocketAddress() + ": " + receptor);

            }

        } catch (IOException ex) {
            //Trata Conexão
            System.out.println("Problema no tratamento da conexão com o cliente "
                    + socket.getInetAddress());
            System.out.println("Erro: " + ex.getMessage());

        }
    }

    public static void main(String[] args) {

        try {
            ChatServer server = new ChatServer();
            server.criarServerSocket(5555);
            System.out.println("..................Aguardando Conexão Cliente 1...............");
            Socket socket = server.esperaConexao();
            System.out.println("Cliente " + socket.getRemoteSocketAddress() + " Conectado");
            server.trataConexao(socket);

        } catch (IOException ex) {
            //TRATA ERRO
        }
    }

}
