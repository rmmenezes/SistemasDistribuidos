package atividades_comunicação_tcp.Exercicio03;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
public static void main(String[] args){
            Socket clienteSocket = null;
            try {
                // isso so faz uma vez... logo quando executa!
                int porta_servidor = 6666;   
                InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");

                clienteSocket = new Socket(endereco_servidor, porta_servidor);
                
                ClientThreadEnviar ThreadEnviar = new ClientThreadEnviar(clienteSocket);
                ThreadEnviar.run();
                
            } catch (EOFException eofe){
                System.out.println("EOF: " + eofe.getMessage());
            } catch (IOException ioe){
                System.out.println("IOE: " + ioe.getMessage());
        }
    }
}

class ClientThreadEnviar extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clienteSocket;
    
    //metodo construtor
    public ClientThreadEnviar(Socket clienteSocket){
        try {
            this.clienteSocket = clienteSocket;
            in = new DataInputStream(clienteSocket.getInputStream());
            out = new DataOutputStream(clienteSocket.getOutputStream());
        } catch (IOException ioe){
            System.out.println("Connection:" + ioe.getMessage());
        }
    }
    
    //metodo a ser executado assim que invocada a classe
    @Override
    public void run(){
        try {
            Scanner leitor_teclado = new Scanner(System.in);
            String buffer = "";
            
            // isso faz infinitas vezes ...
            while(true) {
                System.out.print("Mensagem: ");
                buffer = leitor_teclado.nextLine(); //preenche o buffer com a mensagem
                
                out.writeUTF(buffer);               //envia pro servidor
                
                if (buffer.equals("EXIT")) break;
                
                String buffer_server = in.readUTF();              //aguarda resposta e coloca no buffer
                System.out.println(buffer_server);
                if (buffer.equals("FILES")){
                    int numero_arquivos = Integer.parseInt(buffer_server);
                    for (int i=0; i<numero_arquivos; i++){
                        String nome_arquivo = in.readUTF(); 
                        System.out.println(nome_arquivo);
                    }
                }
                if (buffer.equals("DOWN")){
                    int current = 0;
                    int FILE_SIZE = 21;
                    byte [] mybytearray  = new byte [FILE_SIZE];
                    InputStream is = clienteSocket.getInputStream();
                    FileOutputStream fos = new FileOutputStream("C:\\ProgramData\\NEWteste.txt");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int bytesRead = is.read(mybytearray,0,mybytearray.length);
                    current = bytesRead;
                    
                    do {
                        bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
                        if(bytesRead >= 0) current += bytesRead;
                    } while(bytesRead > -1);
                    
                    bos.write(mybytearray, 0 , current);
                    bos.flush();
                    System.out.println("File " + "C:\\ProgramData\\NEWteste.txt" + " downloaded (" + current + " bytes read)");
                    
                    if (fos != null) fos.close();
                    if (bos != null) bos.close();
                }
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