package atividades_comunicação_udp.Exercicio02;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String args[]) throws InterruptedException {

        try {
            DatagramSocket dgramSocket = new DatagramSocket(); //cria um socket datagrama
           
            InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");
            int porta_servidor = 6666;
            
            File file = new File("C:\\ProgramData\\rafael.txt");
            InputStream is = new FileInputStream(file);
            long length = file.length();
            int tamData = 1024;                      //tamanho do data do datagrama
            byte[] bytes = new byte[tamData];
            long offset = 0;
            int a;
            
            while (offset < length) {
                a = is.read(bytes, 0, tamData);
                DatagramPacket request = new DatagramPacket(bytes, a, endereco_servidor, porta_servidor);
                dgramSocket.send(request);
               
                offset += a;
            }
            //quando acabar de enviar o file ele manda um EXIT
            
            String exit = "EXIT";
            byte[] m = exit.getBytes(); // transforma a mensagem em bytes
            DatagramPacket r = new DatagramPacket(m, m.length, endereco_servidor, porta_servidor);
            dgramSocket.send(r);
            
            /* libera o socket */
            dgramSocket.close();
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } //catch
    } //main		      	
} //class
