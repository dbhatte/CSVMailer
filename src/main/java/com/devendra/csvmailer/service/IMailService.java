package com.devendra.csvmailer.service;

import com.devendra.csvmailer.model.Message;

public interface IMailService {

    void sendMail(Message message);
}
