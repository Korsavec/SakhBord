package com.sakhbord.bord.service.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPT = 10;

    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        attemptsCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build(new CacheLoader<>() {

                    @Nonnull
                    @Override
                    public Integer load(@Nonnull final String key) {
                        return 0;
                    }
                });
    }


    public void deleteCache(final String key) {

        attemptsCache.invalidate(key);
    }

    public void addCache(final String key) {

        AtomicInteger attempts = new AtomicInteger();

        try {
            attempts.set(attemptsCache.get(key));

        } catch (final ExecutionException e) {
            attempts.set(0);

        }

        attempts.getAndIncrement();
        attemptsCache.put(key, attempts.intValue());
    }


    public boolean isBlocked(final String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {

            return false;
        }
    }

}
