package com.hackmann.polarity.websocket;

import com.hackmann.polarity.Globals;
import com.hackmann.polarity.events.Event;
import com.hackmann.polarity.events.EventListener;
import com.hackmann.polarity.events.Interpreter;
import com.hackmann.polarity.user.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class Connection implements Runnable{

    @Autowired
    private Interpreter interpreter;

    @Autowired
    private EventListener listener;

    @Autowired
    private UserService userService;


    private Socket socket;
    private BufferedInputStream in;
    private OutputStream out;

    public int id;
    private boolean running = false;
    private final int HEADER_SIZE = Globals.HEADER_SIZE;

    public Connection(Socket socket, int id) {
        System.out.println("\ncorrect constructor\n");
        this.socket = socket;
        this.id = id;

        try {
            this.out = socket.getOutputStream();
            this.in = new BufferedInputStream(socket.getInputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            running = true;

            while(running) {
                try {
                    byte[] header = new byte[this.HEADER_SIZE];
                    this.in.read(header);

                    if (header == new byte[this.HEADER_SIZE]) {
                        continue;
                    }

                    int length = this.interpreter.getLength(header);

                    if (length == 0) {
                        continue;
                    }

                    byte[] data = new byte[length];
                    this.in.read(data);

                    Event event = this.interpreter.bytesToEvent(data);

                    listener.received(event, this);
                } catch (SocketException e) {
                    userService.getUser(this).disconnect();
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
            userService.getUser(this).disconnect();
        }
    }

    public void close() {
        try {
            running = false;
            in.close();
            out.close();
            socket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Event event) {
        byte[] data = this.interpreter.eventToBytes(event);
        int length = data.length;

        String header = Integer.toString(length);
        for (int i = header.length(); i < this.HEADER_SIZE; i++){
            header = header + "-";
        }

        byte[] bytesHeader = header.getBytes();

        byte[] finalData = new byte[bytesHeader.length + data.length];
        for (int i = 0; i < bytesHeader.length; i++){
            finalData[i] = bytesHeader[i];
        }
        for (int i = 10; i < finalData.length; i++){
            finalData[i] = data[i-10];
        }
        try {
            out.write(finalData);
            out.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
