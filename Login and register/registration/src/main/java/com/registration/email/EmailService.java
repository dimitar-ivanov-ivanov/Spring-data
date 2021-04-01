package com.registration.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    /* use this class to log when we send an email */
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async //this doesn't block the client
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender
                    .createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper
                    (mimeMessage, "utf-8");
            helper.setText(email, true); //pass html email
            helper.setTo(to);
            helper.setSubject("Confirm your email.");
            helper.setFrom("dimitar.ivanov.ivanov99@gmail.com");
        } catch (MessagingException ex) {
            //we don't want to send this exception to the user
            LOGGER.error("failed to send email");
            throw new IllegalStateException("failed to send email");
        }
    }
}
