package atividades_comunicação_udp.Exercicio01;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor{
    public static void main(String args[]){ 
    	DatagramSocket dgramSocket = null;
        try{
            dgramSocket = new DatagramSocket(6666); // cria um socket datagrama em uma porta especifica
            String name_files = "";
            File folder = new File("C:\\ProgramData\\");
            File[] listOfFiles;

            while(true){
                byte[] buffer = new byte[1000]; // cria um buffer para receber requisições

                /* cria um pacote vazio */
                DatagramPacket dgramPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(dgramPacket);  // aguarda a chegada de datagramas
                
                String msg_cliente = new String(dgramPacket.getData(), 0, dgramPacket.getLength());
                System.out.println("Cliente: " + msg_cliente);
                
                if(msg_cliente.equals("EXIT")){
                    break;
                }
                
                name_files = "";
                listOfFiles = folder.listFiles();
                
                
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        name_files += "\n" + file.getName();
                        
                        byte[] req_res = "2".getBytes();
                        byte[] name_file_size = Integer.toString(file.getName().length()).getBytes();
                        byte[] name_file = file.getName().getBytes();

                        byte[] msg_resposta_item = new byte[req_res.length + name_file_size.length + name_file.length];
                        System.arraycopy(req_res, 0, msg_resposta_item, 0, req_res.length);
                        System.arraycopy(name_file_size, 0, msg_resposta_item, req_res.length, name_file_size.length);
                        System.arraycopy(name_file, 0, msg_resposta_item, req_res.length + name_file_size.length, name_file.length);
                        
                        DatagramPacket r = new DatagramPacket(msg_resposta_item, msg_resposta_item.length, dgramPacket.getAddress(), dgramPacket.getPort());
                        dgramSocket.send(r);

                    }
                }
                
                //PARA INDICAR QUE TERMINOU
                byte[] req_res = "2".getBytes();
                byte[] name_file_size = "0".getBytes();
                byte[] name_file = "0".getBytes();

                byte[] msg_resposta_item = new byte[req_res.length + name_file_size.length + name_file.length];
                System.arraycopy(req_res, 0, msg_resposta_item, 0, req_res.length);
                System.arraycopy(name_file_size, 0, msg_resposta_item, req_res.length, name_file_size.length);
                System.arraycopy(name_file, 0, msg_resposta_item, req_res.length + name_file_size.length, name_file.length);

                DatagramPacket r = new DatagramPacket(msg_resposta_item, msg_resposta_item.length, dgramPacket.getAddress(), dgramPacket.getPort());
                dgramSocket.send(r);
                //PARA INDICAR QUE TERMINOU

                
                
               
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


