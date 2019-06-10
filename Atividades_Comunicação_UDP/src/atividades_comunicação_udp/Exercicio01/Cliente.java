package atividades_comunicação_udp.Exercicio01;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String args[]) {
        DatagramSocket dgramSocket;
        
        try {
            dgramSocket = new DatagramSocket(); //cria um socket datagrama
           
            InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");
            int porta_servidor = 6666;

            do {
                String msg = "";
                System.out.println("Mensagem: ");
                Scanner leitor_teclado = new Scanner(System.in);
                msg = leitor_teclado.nextLine(); //preenche o buffer com a mensagem
 
                if (msg.equals("EXIT")) {
                    break;
                }
                
                byte[] m = msg.getBytes(); // transforma a mensagem em bytes

                DatagramPacket request = new DatagramPacket(m, m.length, endereco_servidor, porta_servidor);

                /* envia o pacote */
                dgramSocket.send(request);

                /* cria um buffer vazio para receber datagramas */
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                /* aguarda datagramas */
                dgramSocket.receive(reply);
                System.out.println("Resposta: " + new String(reply.getData(),0,reply.getLength())); // trasforma bits em string do bit 0 ao length()...
                
             
            } while (true);

            /* libera o socket */
            dgramSocket.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } //catch
    } //main		      	
} //class
