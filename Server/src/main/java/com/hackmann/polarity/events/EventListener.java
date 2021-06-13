package com.hackmann.polarity.events;

import com.hackmann.polarity.user.User;
import com.hackmann.polarity.user.UserService;
import com.hackmann.polarity.websocket.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    @Autowired
    private UserService userService;

    public void received(Event event, Connection connection) {
        System.out.println("HERE");
        User user = this.userService.getUser(connection);

        event.run(user);
    }
}
