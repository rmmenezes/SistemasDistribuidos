package atividades_comunicação_udp.Exercicio02;

import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String args[]) throws IOException {
   
        try (FileWriter writer = new FileWriter("C:\\ProgramData\\rafael2.txt", true)) {
            DatagramSocket dgramSocket = new DatagramSocket(6666); // cria um socket datagrama em uma porta especifica
            String msg = "";
            while (true) {
                byte[] buffer = new byte[1024]; // cria um buffer para receber requisições
                DatagramPacket dgramPacket = new DatagramPacket(buffer, buffer.length);
                
                dgramSocket.receive(dgramPacket); // aguarda a chegada de datagramas
                
                msg = (new String(dgramPacket.getData(), 0, dgramPacket.getLength()));
                if (msg.equals("EXIT")){
                    break;
                }
                writer.write(msg);
               }             
            }
        }
    }

       
        

