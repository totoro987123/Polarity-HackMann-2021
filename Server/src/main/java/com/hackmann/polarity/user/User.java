package com.hackmann.polarity.user;

import com.hackmann.polarity.chat.ChatRoom;
import com.hackmann.polarity.pairing.PairingService;
import com.hackmann.polarity.websocket.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

public class User {

    @Autowired
    private PairingService pairingService;

    @Autowired
    private PositionService positionService;

    private Connection connection;
    private ChatRoom currentChatRoom = null;
    private final HashMap<String, Integer> preferences = new HashMap<>();

    public User(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() {
        this.pairingService.removeFromQueues(this);
        this.connection.close();
    }

    public int getPreference(String topic) {
        return this.preferences.get(topic);
    }

    public void setPreference(String topic, int preference) {
        this.preferences.put(topic, preference);
    }

    public void sayHi() {
        System.out.println("Hello, world!");
    }

    public void joinQueue(String topicName) {
        this.pairingService.getQueue(topicName).addUserToQueue(this);
    }

    public void joinRoom(ChatRoom chatroom) {
        this.currentChatRoom = chatroom;
        chatroom.join(this);
    }

    public String getUsername() {
        int currentPosition = this.getPreference(this.getChatRoom().getTopic());
        return this.positionService.getPositionText(currentPosition);
    }

    public void leaveChatRoom() {
        if (this.currentChatRoom != null) {
            this.currentChatRoom.shutdown();
            this.currentChatRoom = null;
        }
    }

    public ChatRoom getChatRoom() {
        return this.currentChatRoom;
    }
}
