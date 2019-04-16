package atividades_comunicação_tcp.Exercicio01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Exercicio02_Servidor {
    public static void main(String[] args) throws InterruptedException{
            Socket clienteSocket = null;
            try {
                // isso so faz uma vez... logo quando executa!
                int porta_servidor = 6666;   

                ServerSocket servidorSocket = new ServerSocket(porta_servidor);
                
                while(true){
                    System.out.println("Servidor aguardando conexão ...");
                    Socket clientSocket = servidorSocket.accept();
                    
                    ClientThreadReceber01 ThreadReceber = new ClientThreadReceber01(clientSocket);
                    ClientThreadEnviar01 ThreadEnviar = new ClientThreadEnviar01(clientSocket);

                    ThreadReceber.start();
                    ThreadEnviar.start();
                
                    ThreadReceber.join();
                    ThreadEnviar.join();
                }
            } catch (EOFException eofe){
                System.out.println("EOF: " + eofe.getMessage());
            } catch (IOException ioe){
                System.out.println("IOE: " + ioe.getMessage());
        }
    }
}

class ClientThreadEnviar01 extends Thread {
    DataOutputStream out;
    Socket clienteSocket;
    
    //metodo construtor
    ClientThreadEnviar01(Socket clienteSocket){
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

class ClientThreadReceber01 extends Thread {
    DataInputStream in;
    Socket clienteSocket;  
    
    //metodo construtor
    ClientThreadReceber01(Socket clienteSocket){
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
                System.out.println("O Cliente disse: " + buffer);
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