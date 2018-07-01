package com.devendra.csvmailer.model;

public class Message {

    private Recipient recipient;

    private String subject;

    private String body;

    private static Message message;

    public Message(Recipient recipient, String subject, String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public static Message getInstance() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "recipient=" + recipient +
                '}';
    }
}
