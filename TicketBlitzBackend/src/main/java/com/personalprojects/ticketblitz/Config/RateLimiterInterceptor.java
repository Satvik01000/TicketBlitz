package com.personalprojects.ticketblitz.Config;

import com.PersonalProjects.TokenBucket.TokenBucketRateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {
    private final TokenBucketRateLimiter rateLimiter;

    public RateLimiterInterceptor(
            @Value("${ticketblitz.ratelimiter.capacity}") int capacity,
            @Value("${ticketblitz.ratelimiter.refill-rate}") int refillRate) {
        this.rateLimiter = new TokenBucketRateLimiter(capacity, refillRate);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        boolean isAllowed = rateLimiter.allowRequest(ip);
        if(!isAllowed) {
            response.setStatus(429);
            response.getWriter().write("429 - Too Many Requests. Try later.");
            return false;
        }
        return true;
    }
}
