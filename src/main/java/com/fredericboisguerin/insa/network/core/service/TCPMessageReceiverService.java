package com.fredericboisguerin.insa.network.core.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageReceiverService implements MessageReceiverService {

    private ServerSocket serverSocket;
    private Socket chatSocket;
    private InputStreamReader stream;
    private BufferedReader reader;
    private IncomingMessageListener monIncomingMessageListenner;


    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        //throw new UnsupportedOperationException();

        serverSocket = new ServerSocket(port);
        chatSocket = serverSocket.accept();
        stream = new InputStreamReader(chatSocket.getInputStream());
        reader = new BufferedReader(stream);
        monIncomingMessageListenner = incomingMessageListener;
        new Thread(()-> this.run()).start();
    }



    public void run(){

        boolean threadteceptionactive = true;
        while (threadteceptionactive){
            try{
                String message = reader.readLine();
                if (message!= null) {
                    monIncomingMessageListenner.onNewIncomingMessage(message);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
