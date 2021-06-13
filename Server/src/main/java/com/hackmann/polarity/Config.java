package com.hackmann.polarity;

import com.hackmann.polarity.chat.ChatRoom;
import com.hackmann.polarity.user.User;
import com.hackmann.polarity.websocket.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.Socket;

@Configuration
public class Config {

    @Bean
    @Scope("prototype")
    public Connection connection(Socket socket, int id) {
        return new Connection(socket, id);
    }

    @Bean
    @Scope("prototype")
    public User user(Connection connection) {
        return new User(connection);
    }

}
