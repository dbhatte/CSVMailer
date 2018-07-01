package com.devendra.csvmailer.service;

import com.devendra.csvmailer.model.Message;

public class MockMailService implements IMailService {


    public void sendMail(Message message) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sent message to " + message);
    }
}
