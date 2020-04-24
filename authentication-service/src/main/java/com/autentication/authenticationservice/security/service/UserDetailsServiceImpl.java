package com.autentication.authenticationservice.security.service;

import com.autentication.authenticationservice.security.block.LoginAttemptService;
import com.autentication.authenticationservice.security.entities.AppUser;
import com.autentication.authenticationservice.security.exception.UserIsBlocked;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    /*
        @Autowired
        private UserRepository userRepository;
    */
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


        final List<AppUser> users = Arrays.asList(
                new AppUser(1, "omar", encoder.encode("12345"), "USER"),
                new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
        );

        for (AppUser appUser : users) {
            if (appUser.getUserName().equals(userName)) {

                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getUserRole());


                return new User(appUser.getUserName(), appUser.getPassword(), grantedAuthorities);
            }
        }

        //if myUser not found
        log.error("User with username: " + userName + " not found in the database");
        throw new UsernameNotFoundException("UserName: " + userName + " not found");





       /* MyUser myUser = userRepository.findByUsername(userName);

        if (myUser == null) {
            throw new UsernameNotFoundException("UserName: " + userName + " not found");
        }
*/

        /*
         if (user == null) {
                return new org.springframework.security.core.userdetails.User(
                  " ", " ", true, true, true, true,
                  getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
            }

            return new org.springframework.security.core.userdetails.User(
              user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
              getAuthorities(user.getRoles()));

        */


        //  return toUserDetails(myUser);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


    /*private UserDetails toUserDetails(MyUser myUser) {

        User.UserBuilder builder = User.withUsername(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(String.valueOf(myUser.getUserRole()));

        return builder.build();

    }*/
}