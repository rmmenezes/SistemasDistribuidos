package sd_atv_01;
// TCP SERVER

import java.net.*;
import java.io.*;

public class SD_Atv_01 {

    public static void main(String[] args) {
        try {
            int servidorPorta = 3333;
            ServerSocket listenSocket = new ServerSocket(servidorPorta);
            while(true){
                System.out.println("Servidor em listen ...");
                Socket clienteSocket = listenSocket.accept();
                System.out.println("Cliente conectardo ... Adicionando Thread ...");
                ClientThread clienteThread = new ClientThread(clienteSocket);
                clienteThread.start();
            }
        } catch (IOException e){
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}

class ClientThread extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clienteSocket;
    
    public ClientThread(Socket clienteSocket){
        try {
            this.clienteSocket = clienteSocket;
            in = new DataInputStream(clienteSocket.getInputStream());
            out = new DataOutputStream(clienteSocket.getOutputStream());
        } catch (IOException ioe){
            System.out.println("Connection:" + ioe.getMessage());
        }
    }
    
    @Override
    public void run(){
        try {
            String buffer = "";
            while (true) {
                buffer =  in.readUTF();
                System.out.println("O Cliente disse: " + buffer);
                if(buffer.equals("PARAR")) break;
                
                buffer = buffer + ":OK";
                out.writeUTF(buffer);
            }
        } catch (EOFException eofe){
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe){
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clienteSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
            System.out.println("Thread comunicação cliente finalizada.");
        }
    }
}
