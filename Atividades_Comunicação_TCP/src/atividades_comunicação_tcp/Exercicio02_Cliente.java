// Faça uma interface gráfica para os clientes de chat e que possibilite
// vários clientes enviarem e visualizarem mensagens de 
// os outros clientes conectados.
// Use o TCP.
package atividades_comunicação_tcp;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Exercicio02_Cliente {
         public static void main(String[] args){
            Socket clienteSocket = null;
            try {
                // isso so faz uma vez... logo quando executa!
                int porta_servidor = 3333;   
                InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");

                clienteSocket = new Socket(endereco_servidor, porta_servidor);
                
                ClientThreadEnviar2 ThreadEnviar = new ClientThreadEnviar2(clienteSocket);
                ThreadEnviar.run();
                
            } catch (EOFException eofe){
                System.out.println("EOF: " + eofe.getMessage());
            } catch (IOException ioe){
                System.out.println("IOE: " + ioe.getMessage());
        }
    }
}

class ClientThreadEnviar2 extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clienteSocket;
    
    //metodo construtor
    public ClientThreadEnviar2(Socket clienteSocket){
        try {
            this.clienteSocket = clienteSocket;
            in = new DataInputStream(clienteSocket.getInputStream());
            out = new DataOutputStream(clienteSocket.getOutputStream());
        } catch (IOException ioe){
            System.out.println("Connection:" + ioe.getMessage());
        }
    }
    
    //metodo a ser executado assim que invocada a classe
    @Override
    public void run(){
        try {
            Scanner leitor_teclado = new Scanner(System.in);
            String buffer = "";
            
            // isso faz infinitas vezes ...
            while(true) {
                System.out.print("Mensagem: ");
                buffer = leitor_teclado.nextLine(); //preenche o buffer com a mensagem
                
                out.writeUTF(buffer);               //envia pro servidor
                
                if (buffer.equals("SAIR")) break;
                
                buffer = in.readUTF();              //aguarda resposta e coloca no buffer
                System.out.println("O Servidor disse: " + buffer);
            }
        } catch (EOFException eofe){
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe){
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clienteSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
            System.out.println("Thread comunicação cliente finalizada.");
        }
    }
}