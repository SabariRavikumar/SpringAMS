package org.sabframeworks.ams.appuser;

import org.sabframeworks.ams.constants.ErrorStringConstants;
import org.sabframeworks.ams.constants.TokenConstants;
import org.sabframeworks.ams.register.tokens.ConfirmationToken;
import org.sabframeworks.ams.register.tokens.ConfirmationTokenService;
import org.sabframeworks.ams.security.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public AppUserService(AppUserRepo appUserRepo, BCryptPasswordEncoder passwordEncoder,ConfirmationTokenService confirmationTokenService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmailId(email).orElseThrow(() -> new UsernameNotFoundException(String.format(ErrorStringConstants.USER_NOT_FOUND,email,"email")));
    }

    public String SignUp(AppUser user){
        boolean userExist = appUserRepo.findByEmailId(user.getEmailId()).isPresent();

        if(userExist){
            throw new IllegalStateException(String.format(ErrorStringConstants.USER_EXIST,user.getEmailId()));
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassWord(encodedPassword);

        appUserRepo.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                                                    token,
                                                    LocalDateTime.now(),
                                                    LocalDateTime.now().plusMinutes(TokenConstants.TOKEN_EXPIRES_IN),
                                                    user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        // TODO: send email confirmation

        return token;
    }

    public void enableAppUser(String email){
            appUserRepo.enableAppuser(email);
    }
}
