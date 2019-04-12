package sd_atv_01;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
	public static void main (String args[]) {
	    Socket clientSocket = null; 
            Scanner reader = new Scanner(System.in); // ler mensagens via teclado
            
            try{
                int serverPort = 3333;   
                InetAddress serverAddr = InetAddress.getByName("127.0.0.1");
                
                clientSocket = new Socket(serverAddr, serverPort);  
                
                /* cria objetos de leitura e escrita */
                DataInputStream in = new DataInputStream( clientSocket.getInputStream());
                DataOutputStream out =new DataOutputStream( clientSocket.getOutputStream());
            
                /* protocolo de comunicação */
                String buffer = "";
                while (true) {
                    System.out.print("Mensagem: ");
                    buffer = reader.nextLine(); // lê mensagem via teclado
                
                    out.writeUTF(buffer);      	// envia a mensagem para o servidor
		
                    if (buffer.equals("PARAR")) break;
                    
                    buffer = in.readUTF();      // aguarda resposta do servidor
                    System.out.println("Server disse: " + buffer);
                } 
	    } catch (UnknownHostException ue){
		System.out.println("Socket:" + ue.getMessage());
            } catch (EOFException eofe){
		System.out.println("EOF:" + eofe.getMessage());
            } catch (IOException ioe){
		System.out.println("IO:" + ioe.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException ioe) {
                    System.out.println("IO: " + ioe);;
                }
            }
     } 
} 
