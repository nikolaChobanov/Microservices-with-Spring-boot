package com.autentication.authenticationservice.security.service;

import com.autentication.authenticationservice.security.block.LoginAttemptService;
import com.autentication.authenticationservice.security.entities.AppUser;
import com.autentication.authenticationservice.security.entities.EntityUser;
import com.autentication.authenticationservice.security.exception.UserIsBlocked;
import com.autentication.authenticationservice.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service("userService")
public class UserRepositoryServiceServiceImpl implements UserService {

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
            throw new UserIsBlocked("Your ip has been blocked due to too many failed login attempts");
        }

        Optional<EntityUser> userCheck = userRepository.findByUsername(userName);
        if (!userCheck.isPresent()) {
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
    public Boolean findIfUsernameFree(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return Optional.empty();
    }


    @Override
    public AppUser createUser(AppUser appUser) {
        EntityUser entityUser = EntityUser.fromDto(appUser);
        entityUser.setPassword(encoder.encode(entityUser.getPassword()));

        return Optional.of(userRepository.save(entityUser))
                .map(EntityUser::toDto)
                .get();
    }


}
