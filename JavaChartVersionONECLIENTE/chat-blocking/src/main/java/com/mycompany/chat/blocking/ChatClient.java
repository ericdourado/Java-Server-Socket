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
public class ChatClient {
    private static void MensagemLoop() throws IOException{
        Socket socket = new Socket("localhost", 5555);
        String msg = null;
        String msgreceptora = null;  
        Scanner scanner = new Scanner(System.in);
        
        //Cria objetos de streams de inputs e outputs
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        System.out.print("Digite uma mensagem (ou 'sair' para finalizar):\n");  
        do{
            
            System.out.print("Digite: ");
            msg = scanner.next();

            //envia mensagem pro servidor
            output.writeUTF(msg);
            output.flush();
            //Recebe msg do servidor
            msgreceptora = input.readUTF();
            System.out.println("Mensagem do Servidor: "+socket.getRemoteSocketAddress() +" " + msgreceptora);

           
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
