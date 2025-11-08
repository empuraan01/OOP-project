package com.smartschool.notifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages notifications and messages in the Smart School Management System.
 * This class demonstrates varargs usage for broadcasting messages to multiple recipients.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Varargs overloading (broadcastMessage method)</li>
 *   <li>Collection management</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class NoticeBoard {
    
    private List<Message> messages;
    private List<String> announcements;
    
    /**
     * Constructs a new NoticeBoard.
     */
    public NoticeBoard() {
        this.messages = new ArrayList<>();
        this.announcements = new ArrayList<>();
    }
    
    /**
     * Sends a message to a single recipient.
     * 
     * @param senderId The ID of the sender
     * @param recipientId The ID of the recipient
     * @param content The message content
     * @return The created Message object
     */
    public Message sendMessage(String senderId, String recipientId, String content) {
        Message message = new Message(senderId, recipientId, content);
        messages.add(message);
        return message;
    }
    
    /**
     * Broadcasts a message to multiple recipients using varargs.
     * This demonstrates varargs overloading.
     * 
     * @param message The message content to broadcast
     * @param recipientIds Variable number of recipient IDs
     * @return Number of messages sent
     */
    public int broadcastMessage(String message, String... recipientIds) {
        int count = 0;
        for (String recipientId : recipientIds) {
            Message msg = new Message("SYSTEM", recipientId, message);
            messages.add(msg);
            count++;
        }
        return count;
    }
    
    /**
     * Broadcasts a message from a specific sender to multiple recipients.
     * Another varargs example.
     * 
     * @param senderId The ID of the sender
     * @param message The message content
     * @param recipientIds Variable number of recipient IDs
     * @return Number of messages sent
     */
    public int broadcastMessage(String senderId, String message, String... recipientIds) {
        int count = 0;
        for (String recipientId : recipientIds) {
            Message msg = new Message(senderId, recipientId, message);
            messages.add(msg);
            count++;
        }
        return count;
    }
    
    /**
     * Adds an announcement to the notice board.
     * 
     * @param announcement The announcement text
     */
    public void addAnnouncement(String announcement) {
        announcements.add(announcement);
    }
    
    /**
     * Gets all messages for a specific recipient.
     * 
     * @param recipientId The recipient ID
     * @return List of messages for the recipient
     */
    public List<Message> getMessagesFor(String recipientId) {
        List<Message> userMessages = new ArrayList<>();
        for (Message msg : messages) {
            if (msg.getRecipientId().equals(recipientId)) {
                userMessages.add(msg);
            }
        }
        return userMessages;
    }
    
    /**
     * Gets all announcements.
     * 
     * @return List of announcements
     */
    public List<String> getAnnouncements() {
        return new ArrayList<>(announcements);
    }
    
    /**
     * Gets all messages.
     * 
     * @return List of all messages
     */
    public List<Message> getAllMessages() {
        return new ArrayList<>(messages);
    }
}

