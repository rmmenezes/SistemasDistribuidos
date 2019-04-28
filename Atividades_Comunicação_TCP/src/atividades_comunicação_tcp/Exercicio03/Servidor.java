package atividades_comunicação_tcp.Exercicio03;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                String[] comando = buffer.split(" ");
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
                    List<String> lista_de_arquivos = new ArrayList<>();
                    int numero_de_arquivos = 0;
                    File path = new File("C:\\ProgramData");
                    File[] listOfFiles = path.listFiles();  
                    for (File file : listOfFiles)
                    {
                        numero_de_arquivos = numero_de_arquivos + 1;
                        lista_de_arquivos.add(file.getName());
                    }
                    // Envia o numero de arquivos
                    out.writeUTF(Integer.toString(numero_de_arquivos));
                    
                    for (String file : lista_de_arquivos)
                    {
                        DataOutputStream send = new DataOutputStream(clientSocket.getOutputStream());
                        send.writeUTF(file);
                    }
                } else if (comando[0].equals("DOWN")){
                    File file = new File("C:\\ProgramData\\" + comando[1]);
                    byte [] mybytearray = new byte[(int)file.length()];
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    bis.read(mybytearray,0,mybytearray.length);
                    System.out.println("Sending " + file + "(" + mybytearray.length + " bytes)");
                    out.write(mybytearray,0,mybytearray.length);
                    out.flush();
                    System.out.println("Done.");
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


