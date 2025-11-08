package com.smartschool.notifications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a message in the notification system.
 * Messages can be sent between users or broadcast to multiple recipients.
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class Message {
    
    private String senderId;
    private String recipientId;
    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;
    
    /**
     * Constructs a new Message.
     * 
     * @param senderId The ID of the message sender
     * @param recipientId The ID of the message recipient
     * @param content The message content
     */
    public Message(String senderId, String recipientId, String content) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }
    
    /**
     * Gets the sender ID.
     * 
     * @return The sender ID
     */
    public String getSenderId() {
        return senderId;
    }
    
    /**
     * Gets the recipient ID.
     * 
     * @return The recipient ID
     */
    public String getRecipientId() {
        return recipientId;
    }
    
    /**
     * Gets the message content.
     * 
     * @return The content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Gets the timestamp.
     * 
     * @return The timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Checks if the message has been read.
     * 
     * @return true if read, false otherwise
     */
    public boolean isRead() {
        return isRead;
    }
    
    /**
     * Marks the message as read.
     */
    public void markAsRead() {
        this.isRead = true;
    }
    
    /**
     * Returns a string representation of the message.
     * 
     * @return Formatted message string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] From: %s | To: %s | %s", 
                           timestamp.format(formatter), senderId, recipientId, content);
    }
}

