package com.mycompany.chat.blocking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ericd
 */
public class ReaderAndWriter {

    //GRAVAR ARQUIVO
    public void Write(String Caminho, String Texto) {
        try {
            FileWriter arq = new FileWriter(Caminho,true);
            PrintWriter gravarArq = new PrintWriter(arq);
            //gravarArq.println(Texto);
            gravarArq.println(Texto);
            gravarArq.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
}
