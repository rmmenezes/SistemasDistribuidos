package atividades_comunicação_tcp.Exercicio01;
// 1) Fazer um código para o Cliente e Servidor se comunicarem.
// O cliente envia e recebe mensagens.
// O servidor envia e recebe mensagens. 
// Quando algum dos dois enviar 'SAIR', a comunicação entre eles deve ser finalizada.
// Use o TCP.

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Exercicio01_Cliente {
        public static void main(String[] args) throws InterruptedException{
            Socket clienteSocket = null;
            try {
                // isso so faz uma vez... logo quando executa!
                int porta_servidor = 6666;   
                InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");

                clienteSocket = new Socket(endereco_servidor, porta_servidor);
                
                ClientThreadReceber ThreadReceber = new ClientThreadReceber(clienteSocket);
                ClientThreadEnviar ThreadEnviar = new ClientThreadEnviar(clienteSocket);
                
                ThreadReceber.start();
                ThreadEnviar.start();
                
                ThreadReceber.join();
                ThreadEnviar.join();

            } catch (EOFException eofe){
                System.out.println("EOF: " + eofe.getMessage());
            } catch (IOException ioe){
                System.out.println("IOE: " + ioe.getMessage());
        }
    }
}

class ClientThreadEnviar extends Thread {
    DataOutputStream out;
    Socket clienteSocket;
    
    //metodo construtor
    ClientThreadEnviar(Socket clienteSocket){
        try {
            this.clienteSocket = clienteSocket;
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
            }
        } catch (EOFException eofe){
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe){
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                out.close();
                clienteSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
            System.out.println("Thread comunicação cliente finalizada.");
        }
    }
}

class ClientThreadReceber extends Thread {
    DataInputStream in;
    Socket clienteSocket;  
    
    //metodo construtor
    ClientThreadReceber(Socket clienteSocket){
        try {
            this.clienteSocket = clienteSocket;
            in = new DataInputStream(clienteSocket.getInputStream());
        } catch (IOException ioe){
            System.out.println("Connection:" + ioe.getMessage());
        }
    }
    
    //metodo a ser executado assim que invocada a classe
    @Override
    public void run(){
        try {
            // isso faz infinitas vezes ...
            while(true) {
                String buffer = in.readUTF();             //aguarda resposta e coloca no buffer
                System.out.println("O Servidor disse: " + buffer);
                if (buffer.equals("SAIR")) break;

            }
        } catch (EOFException eofe){
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe){
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                in.close();
                clienteSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
            System.out.println("Thread comunicação cliente finalizada.");
        }
    }
}