package com.smartschool.notifications;

/**
 * Interface for notification capabilities.
 * Classes implementing this interface can send and receive notifications
 * within the Smart School Management System.
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public interface Notifier {
    
    /**
     * Sends a notification to another user.
     * 
     * @param recipientId The ID of the user receiving the notification
     * @param message The message content to send
     * @return true if notification was sent successfully, false otherwise
     */
    boolean sendNotification(String recipientId, String message);
    
    /**
     * Receives a notification from the system.
     * 
     * @param message The notification message received
     */
    void receiveNotification(String message);
}

