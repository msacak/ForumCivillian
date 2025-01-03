package com.sacak.forumcivillian.service;


import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.VerificationToken;
import com.sacak.forumcivillian.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;


    private String fromAddress = "no-reply@forumcivillian.com";
    private String url = "http://localhost:9090/v1/dev";

    private SimpleMailMessage makeMailMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        return message;
    }

    public void sendVerificationMail(VerificationToken verificationToken)  {
        SimpleMailMessage message = makeMailMessage();
        Optional<User> userOptional = userRepository.findById(verificationToken.getUserId());
        if(userOptional.isPresent()) {
            User user = userOptional.get();  message.setTo(user.getEmail());
            message.setSubject("Verify your email to active your account.");
            message.setText("Please follow the link below to verify your email to active your account.\n" +
                    url+"/user/verify?token="+verificationToken.getToken());
            mailSender.send(message);
        }
    }


    public void sendResetPasswordMail(String email, String token) {
        SimpleMailMessage message = makeMailMessage();
        message.setTo(email);
        message.setSubject("Reset your password");
        message.setText("Please follow the link below to reset your password.\n"
                +"http://localhost:3000/reset-password/"+token);
        mailSender.send(message);

    }
}