package com.globallogic.service;

import com.globallogic.model.EmailRequest;

public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
}
