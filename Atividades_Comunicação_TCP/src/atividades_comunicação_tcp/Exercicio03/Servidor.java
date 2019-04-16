package atividades_comunicação_tcp.Exercicio03;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {
    public static void main(String args[]) throws InterruptedException {
        try {
            int serverPort = 6666; 
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                System.out.println("Servidor aguardando conexao ...");
                Socket clientSocket = listenSocket.accept();
                System.out.println("Cliente conectado ... Criando thread ...");
                ClientThread c = new ClientThread(clientSocket);
                c.start();
                c.join();
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    } 
}


class ClientThread extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ioe) {
            System.out.println("Connection:" + ioe.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String buffer = "";
            while (true) {
                buffer = in.readUTF();  
                System.out.println("Cliente disse: " + buffer);
                
                if (buffer.equals("TIME")){
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                    String time = sdf.format(new Date());
                    out.writeUTF(time);
                }
                else if (buffer.equals("DATE")){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String data = sdf.format(new Date());
                    out.writeUTF(data);
                }
                else if (buffer.equals("FILES")){
                    File path = new File("C:\\Users\\Rafael Menezes\\Documents\\NetBeansProjects\\Atividades_Comunicação_TCP\\src\\atividades_comunicação_tcp\\Exercicio03\\DiretorioCompartilhado");
                    File[] listOfFiles = path.listFiles();  
                    for (File file : listOfFiles)
                    {
                        out.writeUTF(file.getName());
                    }
                }
                else if (buffer.equals("EXIT")) break;
                
            }
        } catch (EOFException eofe) {
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
        }
        System.out.println("Thread comunicação cliente finalizada.");
    } //run

    private String String(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} //class


