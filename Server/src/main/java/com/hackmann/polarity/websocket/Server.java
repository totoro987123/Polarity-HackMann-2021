package com.hackmann.polarity.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Controller
public class Server implements Runnable {

    @Autowired
    private ApplicationContext context;

    private final int PORT = 16568;
    private ServerSocket serverSocket;
    private boolean running = false;
    private int id = 0;

    public Server() {
        try {
            serverSocket = new ServerSocket(this.PORT);
        }catch(IOException e) {
            e.printStackTrace();
        }

        this.start();
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        this.running = true;
        System.out.println(String.format("\n\nServer started on port: %s\n", this.PORT));

        while (this.running) {
            try {
                Socket socket = serverSocket.accept();
                this.initSocket(socket);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        this.shutdown();
    }

    private void initSocket(Socket socket) {
        Connection connection = context.getBean(Connection.class, socket, id);

        new Thread(connection).start();
        this.id++;
    }

    public void shutdown() {
        this.running = false;

        try {
            serverSocket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
