package atividades_comunicação_tcp.Exercicio04;
import atividades_comunicação_tcp.Exercicio03.*;
import static com.sun.management.jmx.Trace.send;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            String buffer = "";
            while (true) {
                buffer = in.readUTF();  
                System.out.println("Cliente disse: " + buffer);
                String[] comando = buffer.split(" ");
                if (buffer.equals("DELETE")){
                }
                else if (buffer.equals("GETFILESLIST")){
                    int i;
                    File diretorio = new File("C:\\ProgramData\\");
                    File arquivos[] = diretorio.listFiles();
                    for (i = 0; i < arquivos.length; i++) {
                        File arquivo = arquivos[i];
                        String nome = arquivo.getName();
                        out.writeUTF(nome);
                    }
                    out.writeUTF("Quantidade de Arquivos: " + i);
                }
                else if (buffer.equals("FILES")){
                } else if (comando[0].equals("DOWN")){
                    File diretorio = new File("C:\\ProgramData\\");
                    File files[] = diretorio.listFiles();
                    boolean oi = false;
                    for (int i = 0; i < files.length; i++) {
                        File arquivo = files[i];
                        String nome = arquivo.getName();
                        if (comando[1] == null ? nome == null : comando[1].equals(nome)) {
                            // se o arquivo existir seta true
                            oi = true;
                        }
                    }
                    if (oi) {
                        // instancia um objeto File (arquivo requisitado)
                        File arquivo = new File("C:\\ProgramData\\" + comando[1]);
                        FileInputStream fis = new FileInputStream(arquivo);
                        byte b[] = new byte[(int) arquivo.length()];
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        bis.read(b, 0, b.length);

                        // manda nome do arquivo o tamanho e o arquivo
                        //out.writeUTF("Arquivo: " + arquivo.getName());
                        //out.writeInt((int) arquivo.length());
                        out.write(b, 0, b.length);
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
    }
} //class


