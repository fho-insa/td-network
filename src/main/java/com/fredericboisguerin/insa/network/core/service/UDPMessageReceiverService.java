package com.fredericboisguerin.insa.network.core.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class UDPMessageReceiverService implements MessageReceiverService, Runnable {

    private byte[] dataReception;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private IncomingMessageListener monIncomingMessageListenner;

    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        //throw new UnsupportedOperationException();


        dataReception = new byte[100];
        datagramSocket = new DatagramSocket(port);
        datagramPacket = new DatagramPacket(new byte[100],100);
        monIncomingMessageListenner = incomingMessageListener;

        new Thread(()-> this.run()).start();

    }
    public void messageAffichage(){

        dataReception = datagramPacket.getData();
        String chaineEnvoyee = new String(dataReception, datagramPacket.getOffset(), datagramPacket.getLength());


        monIncomingMessageListenner.onNewIncomingMessage(chaineEnvoyee);
    }

    public void run(){
        boolean threadteceptionactive = true;
        while (threadteceptionactive){
            try{
                datagramSocket.receive(datagramPacket);
                messageAffichage();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
