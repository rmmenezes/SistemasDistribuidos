package atividades_comunicação_udp.Exercicio01;

import java.net.*;
import java.io.*;

public class Servidor{
    public static void main(String args[]){ 
    	DatagramSocket dgramSocket = null;
        try{
            dgramSocket = new DatagramSocket(6666); // cria um socket datagrama em uma porta especifica
            
            while(true){
                byte[] buffer = new byte[1000]; // cria um buffer para receber requisições

                /* cria um pacote vazio */
                DatagramPacket dgramPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(dgramPacket);  // aguarda a chegada de datagramas
                
                String msg_cliente = new String(dgramPacket.getData(), 0, dgramPacket.getLength());
                System.out.println("Cliente: " + msg_cliente);
                
                if(msg_cliente.equals("EXIT")){
                    break;
                              
                } else if(msg_cliente.equals("EXIT")){
                    break;
                }
                
                
                
                DatagramPacket reply = new DatagramPacket(dgramPacket.getData(), dgramPacket.getLength(), dgramPacket.getAddress(), dgramPacket.getPort()); // cria um pacote com os dados

                dgramSocket.send(reply); // envia o pacote
            } //while
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            dgramSocket.close();
        } //finally
    } //main
}//class
