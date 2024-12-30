package com.sacak.forumcivillian.service;

import com.sacak.forumcivillian.dto.request.RegisterRequest;
import com.sacak.forumcivillian.dto.request.ResetPasswordRequest;
import com.sacak.forumcivillian.dto.request.UserLoginRequest;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.entity.VerificationToken;
import com.sacak.forumcivillian.entity.enums.EState;
import com.sacak.forumcivillian.entity.enums.EUserRank;
import com.sacak.forumcivillian.entity.enums.EUserRole;
import com.sacak.forumcivillian.exceptions.ErrorType;
import com.sacak.forumcivillian.exceptions.ForumCivillianException;
import com.sacak.forumcivillian.repository.UserRepository;
import com.sacak.forumcivillian.repository.VerificationTokenRepository;
import com.sacak.forumcivillian.utility.EncryptionService;
import com.sacak.forumcivillian.utility.JwtManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JwtManager jwtManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

    @Transactional
    public User register(RegisterRequest dto){
        if(!dto.password().equals(dto.rePassword())) throw new ForumCivillianException( ErrorType.PASSWORDS_DO_NOT_MATCH);

        String crytedPassword = encryptionService.encryptPassword(dto.password());

        User user = userRepository.save(User.builder()
                .userName(dto.userName())
                .password(crytedPassword)
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .userRole(EUserRole.USER)
                .build());
        VerificationToken verificationToken =createVerificationToken(user);
        emailService.sendVerificationMail(verificationToken);
        verificationTokenRepository.save(verificationToken);
        return user;

    }

    private VerificationToken createVerificationToken(User user) {
        return VerificationToken.builder()
                .token(jwtManager.createVerificationToken(user))
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .userId(user.getId())
                .build();
    }

    @Transactional
    public String login(UserLoginRequest dto){
        Optional<User> userOpt = userRepository.findByUserName(dto.userName());
        if(userOpt.isPresent()){
            User user = userOpt.get();
            if(encryptionService.checkPassword(dto.password(), user.getPassword())){
                if(user.getIsVerified()){
                    return jwtManager.createUserToken(user.getId());
                }
                else{
                    List<VerificationToken> verificationTokens = verificationTokenRepository.findAllByUserIdOrderByCreatedTimeDesc(user.getId());
                    boolean reSend = verificationTokens.isEmpty()
                            || verificationTokens.getFirst().getCreatedTime().before(new Timestamp(System.currentTimeMillis()-(60*60*1000)));
                    if(reSend){
                        VerificationToken verificationToken =  createVerificationToken(user);
                        verificationTokenRepository.save(verificationToken);
                        emailService.sendVerificationMail(verificationToken);
                    }
                    throw new ForumCivillianException(ErrorType.USER_NOT_VERIFIED);
                }

            }
            throw new ForumCivillianException(ErrorType.WRONG_PASSWORD);
        }
        throw new ForumCivillianException(ErrorType.USER_NOT_FOUND);
    }

    @Transactional
    public boolean verifyUser(String token){
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        if(verificationTokenOptional.isPresent()) {
            VerificationToken verificationToken = verificationTokenOptional.get();
            Long userId = verificationToken.getUserId();
            User user = userRepository.findById(userId).get();
            if(!user.getIsVerified()){
                user.setIsVerified(true);
                userRepository.save(user);
                verificationTokenRepository.deleteByUserId(userId);
                return true;
            }
        }
        return false;
    }

    public Boolean forgotPassword(String email){
        Optional<User> userOpt = userRepository.findByEmailAndState(email,EState.ACTIVE);
        if(userOpt.isPresent()){
            String token = jwtManager.createResetPasswordToken(email);
            emailService.sendResetPasswordMail(email,token);
            return true;
        }
        throw new ForumCivillianException(ErrorType.EMAIL_NOT_FOUND);
    }

    public Boolean resetPassword(String token, ResetPasswordRequest dto){
        String email = jwtManager.validatePasswordResetToken(token).orElseThrow(()->new ForumCivillianException(ErrorType.INVALID_TOKEN));
        User user = userRepository.findByEmailAndState(email,EState.ACTIVE).orElseThrow(()->new ForumCivillianException(ErrorType.USER_NOT_FOUND));
        if(!dto.password().equals(dto.rePassword())) throw new ForumCivillianException(ErrorType.PASSWORDS_DO_NOT_MATCH);
        user.setPassword(encryptionService.encryptPassword(dto.password()));
        userRepository.save(user);
        return true;
    }



    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ForumCivillianException(ErrorType.USER_NOT_FOUND));
    }

    public String findUserNameByUserId(Long userId) {
        return userRepository.findUsernameByUserId(userId).orElseThrow(()-> new ForumCivillianException(ErrorType.USER_NOT_FOUND));
    }


    public void save(User user) {
        userRepository.save(user);
    }
}
