package com.example.service;


public interface MailService {

    /**
     * Send Email
     *
     * @param to      receiver
     * @param subject Subject
     * @param content content
     */
    void sendMail(String to, String subject, String content) throws Exception;

}
