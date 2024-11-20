package com.example.dto;

/**
 * A Data Transfer Object (DTO) for updating a message.
 */
public class MessageUpdateRequest {

    /**
     * The updated text of the message.
     */
    private String messageText;

    /**
     * Retrieves the updated message text.
     *
     * @return the updated message text.
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets the updated message text.
     *
     * @param messageText the new message text as a {@code String}.
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}