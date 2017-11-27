package com.fredericboisguerin.insa.network.core.service;

import java.io.PrintWriter;
import java.net.Socket;

public class TCPMessageSenderService implements MessageSenderService {
    @Override
    public void sendMessageOn(String ipAddress, int port, String message) throws Exception {
        //throw new UnsupportedOperationException();
        Socket chatsocket =new Socket(ipAddress, port);
        PrintWriter writer = new PrintWriter(chatsocket.getOutputStream());
        writer.println(message);
        writer.close();

    }
}
