package com.hackmann.polarity.user;

import com.hackmann.polarity.websocket.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    private ApplicationContext context;

    public HashMap<Connection, User> users = new HashMap<>();

    public User registerUser(Connection connection) {
        User user = context.getBean(User.class, connection);
        this.users.put(connection, user);
        return user;
    }

    public User getUser(Connection connection) {
        User user = users.get(connection);

        return user != null ? user : this.registerUser(connection);
    }
}
