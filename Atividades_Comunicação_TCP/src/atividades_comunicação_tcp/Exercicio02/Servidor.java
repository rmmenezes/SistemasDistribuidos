package atividades_comunicação_tcp.Exercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
     public static void main(String[] args) throws InterruptedException{
            Socket clienteSocket = null;
            try {
                // isso so faz uma vez... logo quando executa!
                int porta_servidor = 6666;   
                List<Socket> clientes = new ArrayList<>();
                ServerSocket servidorSocket = new ServerSocket(porta_servidor);
                
                while(true){
                    System.out.println("Servidor aguardando conexão ...");
                    Socket clientSocket = servidorSocket.accept();
                    clientes.add(clientSocket);
                    ClientThreadReceber01 ThreadReceber = new ClientThreadReceber01(clientSocket, clientes);
                    ThreadReceber.start();

                }
            } catch (EOFException eofe){
                System.out.println("EOF: " + eofe.getMessage());
            } catch (IOException ioe){
                System.out.println("IOE: " + ioe.getMessage());
        }
    }
}

class ClientThreadReceber01 extends Thread {
    DataInputStream in;
    Socket clienteSocket;  
    List<Socket> usuarios;
    
    //metodo construtor
    ClientThreadReceber01(Socket clienteSocket, List<Socket> clientes){
        try {
            this.clienteSocket = clienteSocket;
            this.usuarios = clientes;
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
                for(Socket user: usuarios){
                    if (user != clienteSocket){
                        DataOutputStream send = new DataOutputStream(user.getOutputStream());
                        send.writeUTF(buffer);
                        System.out.println("DISTRIBUIDO");
                    }
                }
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
