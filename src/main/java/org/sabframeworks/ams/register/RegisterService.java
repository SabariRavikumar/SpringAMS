package org.sabframeworks.ams.register;

import org.sabframeworks.ams.appuser.AppUser;
import org.sabframeworks.ams.appuser.AppUserRole;
import org.sabframeworks.ams.appuser.AppUserService;
import org.sabframeworks.ams.constants.ErrorStringConstants;
import org.sabframeworks.ams.register.tokens.ConfirmationToken;
import org.sabframeworks.ams.register.tokens.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegisterService {

    private final AppUserService appUserService;

    private final ConfirmationTokenService confirmationTokenService;


    public RegisterService(AppUserService appUserService,ConfirmationTokenService confirmationTokenService) {
        this.appUserService = appUserService;
        this.confirmationTokenService = confirmationTokenService;
    }
    public String register(RegisterReuest request){
        String token = appUserService.SignUp(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmailId(),
                request.getPassWord(),
                request.getCountry(),
                request.getState(),
                AppUserRole.USER
        ));

//        String content = "http://localhost:8080/api/v1/register/confirm?token=" + token;
//        emailSender.send(request.getEmailId(), content);
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElseThrow(() -> new IllegalStateException(ErrorStringConstants.TOKEN_NOT_FOUND));

        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException(ErrorStringConstants.ALREADY_CONFIRMED);
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException(ErrorStringConstants.TOKEN_EXPIRED);
        }

        confirmationTokenService.setConfirmedAt(token);

        appUserService.enableAppUser(confirmationToken.getAppUser().getEmailId());

        return "confirmed";

    }
}
