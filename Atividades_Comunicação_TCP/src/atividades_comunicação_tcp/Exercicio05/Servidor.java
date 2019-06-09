package atividades_comunicação_tcp.Exercicio05;

import atividades_comunicação_tcp.Exercicio03.*;
import static com.sun.management.jmx.Trace.send;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            FileWriter writer = new FileWriter("C:\\ProgramData\\log.txt", true);
            ArrayList<String> log = new ArrayList<>(); 
            String buffer;
            
            File folder = new File("C:\\ProgramData\\");
            File[] listOfFiles;

            Path fileLocation;
            byte[] data = new byte[1];
            
            while (true) {
                String name_files;
                boolean hasFile = false;
                buffer = in.readUTF();
                String[] comando = buffer.split(" ");
                System.out.println("Cliente disse: " + buffer);
                
                

                if (comando[0].equals("DELETE")) {
                    System.out.println("############### OPAA é DELETEEE ################");
                    //Log 1 porque o cliente escreveu e mandou algo ou seja (requisiçao)           
                    log.add("1");
                    //Log 1 porque o cliente codigo do comando (DELETE)   
                    log.add("2");
                    
                    
                    listOfFiles = folder.listFiles();
                    name_files = buffer.replaceAll("DELETE ", "");

                    //nesta parte eu removo do delete da minha string e soh fica o nome do arquivo
                    log.add(Integer.toString(name_files.getBytes().length)); //add ao log o tam do arquivo
                    log.add(name_files);                                     //add ao log o nome do arquivo

                    for (File f : listOfFiles) {
                        if (f.isFile() && f.getName().equals(name_files)) {
                            hasFile = true;
                            f.delete();
                        }
                    }
                    
                    if (hasFile) {
                        log.add("1");
                        buffer = "Removido com Sucesso" + name_files;
                        out.writeUTF(buffer);
                    } else {
                        log.add("2");
                        buffer = "Arquivo não encontrado";
                        out.writeUTF(buffer);
                    }

                } else if (comando[0].equals("GETFILESLIST")) {
                    name_files = "";
                        listOfFiles = folder.listFiles();

                        for (File file : listOfFiles) {
                            if (file.isFile()) {
                                name_files += "\n" + file.getName();
                            }
                        }
                                                
                        buffer = name_files;
                        out.writeUTF(buffer);
                        
                } else if (comando[0].equals("GETFILE")) {
                    FileInputStream fis;
                    int BUFFER = 4096;
                    byte[] mybytearray = new byte[BUFFER];              
                    fis = new FileInputStream(folder + "\\" + comando[1]);     //nesta parte eu somo o diretorio correte + o nome do arquivo
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    DataInputStream dis = new DataInputStream(bis);
                    try {
                        out.writeLong(folder.length());
                        int read;
                        while ((read = dis.read(mybytearray)) != -1) {
                            out.write(mybytearray, 0, read);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (comando[0].equals("EXIT")) {
                    
                    break;
                }
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
    }
} //class

