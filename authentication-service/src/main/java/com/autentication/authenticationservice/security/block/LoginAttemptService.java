package com.autentication.authenticationservice.security.block;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 6;
    private com.google.common.cache.LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
      //  super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });

    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;

        try {
            attempts = attemptsCache.get(key);

        } catch (ExecutionException e) {
            attempts = 0;
        }
        log.error("Logging failed");
        attempts++;
        attemptsCache.put(key, attempts);
    }


    public boolean isBlocked(String key) {

        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }

}
