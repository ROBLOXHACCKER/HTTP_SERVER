package httpserver;

import java.io.*;
import java.net.*;


public class Server{

    //var
    public static int port = 7777;

    public static ServerSocket S_Socket;
    public static Handler S_Handler;
    
    
    //m
    public static void main(String[] args) {
        try{
            S_start(port);
            S_Handler = new Handler();
            while(true){
                Socket clientSocket = S_Socket.accept();
                S_Handler.handleRequest(clientSocket);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //S_START
    public static void S_start(int port) throws IOException{
        S_Socket = new ServerSocket(port);
        System.out.println("Listen server on port: "+ port);
    }
}
