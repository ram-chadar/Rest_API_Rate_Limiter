package com.vasyerp.service;

import com.vasyerp.entity.User;
import com.vasyerp.exception.CustomizedException;
import com.vasyerp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final int MAX_REQUESTS_PER_HOUR = 10;

    private final Map<String, UserRequestInfo> userRequestMap = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;

    public void checkRateLimit(String apiKey) throws CustomizedException {
        User user = userRepository.findByApiKey(apiKey);
        if (user == null) {
            throw new CustomizedException(101L, "Invalid API key");
        }

        UserRequestInfo requestInfo = userRequestMap.computeIfAbsent(apiKey, k -> new UserRequestInfo());

        LocalDateTime now = LocalDateTime.now();

        if (requestInfo.getStartTime().plusMinutes(10).isBefore(now)) {
            requestInfo.setStartTime(now);
            requestInfo.setRequestCount(0);
        }

        if (requestInfo.getRequestCount() < MAX_REQUESTS_PER_HOUR) {
            requestInfo.incrementRequestCount();
        } else {
            throw new CustomizedException(102L, "Rate limit exceeded");
        }
    }

    
}
