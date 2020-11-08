package com.example.util;

import io.github.biezhi.ome.OhMyEmail;

import java.util.*;

public class EmailUtils {

    /**
     * Email
     *
     * @param smtpHost smtpHost
     * @param userName Address
     * @param password password
     */
    public static void configMail(String smtpHost, String userName, String password) {
        Properties properties = OhMyEmail.defaultConfig(false);
        properties.setProperty("mail.smtp.host", smtpHost);
        OhMyEmail.config(properties, userName, password);
    }


}
