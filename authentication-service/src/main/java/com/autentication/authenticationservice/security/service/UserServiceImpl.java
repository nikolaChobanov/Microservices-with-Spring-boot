package com.autentication.authenticationservice.security.service;

import com.autentication.authenticationservice.security.block.LoginAttemptService;
import com.autentication.authenticationservice.security.entities.AppUser;
import com.autentication.authenticationservice.security.entities.EntityUser;
import com.autentication.authenticationservice.security.exception.BlockedUserException;
import com.autentication.authenticationservice.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final String USERNAME_ALREADY_TAKEN_MESSAGE = "username: '%s' is already taken";

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private LoginAttemptService loginAttemptService;


    @Autowired
    private HttpServletRequest request;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        String ip = getClientIP();

        if (loginAttemptService.isBlocked(ip)) {
            log.info("User with ip: " + ip + " is blocked for too many failed login attempts");
            throw new BlockedUserException("Your ip has been blocked due to too many failed login attempts");
        }

        Optional<EntityUser> userCheck = userRepository.findByUsername(userName);
        if (!userCheck.isPresent()) {
            log.error("User with ip: " + ip + " tried to login with false username");
            throw new UsernameNotFoundException("UserName: " + userName + " not found");
        }

        EntityUser entityUser = userCheck.get();

        return AppUser.builder()
                .id(entityUser.getId())
                .userName(entityUser.getUsername())
                .password(entityUser.getPassword())
                .build();
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


    @Override
    public Optional<AppUser> findByUsername(String username) {
        return Optional.empty();
    }


    @Override
    public AppUser createUser(AppUser appUser) {

        if (userRepository.existsByUsername(appUser.getUsername())) {
            log.error("User tried to register with username that already exists");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format(USERNAME_ALREADY_TAKEN_MESSAGE, appUser.getUsername())
            );
        }

        EntityUser entityUser = EntityUser.fromDto(appUser);
        entityUser.setPassword(encoder.encode(entityUser.getPassword()));
        log.info("User has successfully registered");
        return Optional.of(userRepository.save(entityUser))
                .map(EntityUser::toDto)
                .get();
    }


}
