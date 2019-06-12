package atividades_comunicação_udp.Exercicio01;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String args[]) throws InterruptedException {
        DatagramSocket dgramSocket;
        
        try {
            dgramSocket = new DatagramSocket(); //cria um socket datagrama
           
            InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");
            int porta_servidor = 6666;

            do {
                
                String msg = "";
                System.out.println("Search: ");
                Scanner leitor_teclado = new Scanner(System.in);
                msg = leitor_teclado.nextLine(); //preenche o buffer com a mensagem
 
                if (msg.equals("EXIT")) {
                    break;
                }
                
                byte[] req_res = "1".getBytes();
                byte[] file_type_1 = "1".getBytes();
                byte[] file_type_2 = "1".getBytes();
                byte[] search = msg.getBytes();

                byte[] msg_requisicao = new byte[req_res.length + file_type_1.length + file_type_2.length + search.length];
                System.arraycopy(req_res, 0, msg_requisicao, 0, req_res.length);
                System.arraycopy(file_type_1, 0, msg_requisicao, req_res.length, file_type_1.length);
                System.arraycopy(file_type_2, 0, msg_requisicao, req_res.length + file_type_1.length, file_type_2.length);
                System.arraycopy(search, 0, msg_requisicao, req_res.length + file_type_1.length + file_type_2.length, search.length);

                
                DatagramPacket request = new DatagramPacket(msg_requisicao, msg_requisicao.length, endereco_servidor, porta_servidor);

                /* envia o pacote */
                dgramSocket.send(request);
                
                ClientThreadReceber ThreadReceber = new ClientThreadReceber(dgramSocket);
                ThreadReceber.start();
                ThreadReceber.join();

             
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

class ClientThreadReceber extends Thread {
    DatagramSocket dgramSocket;
    
    ClientThreadReceber(DatagramSocket dgramSocket){
        this.dgramSocket = dgramSocket;
    }
    
    //metodo a ser executado assim que invocada a classe
    @Override
    public void run(){
        try {
            // isso faz infinitas vezes ...
            while(true) {
                /* cria um buffer vazio para receber datagramas */
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                dgramSocket.receive(reply);
                String res = new String(reply.getData(),0,reply.getLength());
                if (res.equals("200")){
                    System.out.println("--- Fim da Busca ---");
                    break;
                }
                System.out.println("File: " + res); // trasforma bits em string do bit 0 ao length()...
            }
        } catch (EOFException eofe){
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe){
            System.out.println("IOE: " + ioe.getMessage());
        }
    }
}