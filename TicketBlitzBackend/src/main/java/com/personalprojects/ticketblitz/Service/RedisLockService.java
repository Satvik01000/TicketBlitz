package com.personalprojects.ticketblitz.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
public class RedisLockService {

  private final StringRedisTemplate redisTemplate;

  public RedisLockService(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public boolean acquireLock(UUID showId, UUID seatId, UUID userId) {
    String key = "lock:show:" + showId + ":seat:" + seatId;

    Boolean success =
        redisTemplate.opsForValue().setIfAbsent(key, userId.toString(), Duration.ofMinutes(10));

    return Boolean.TRUE.equals(success);
  }

  public void releaseLock(UUID showId, UUID seatId, UUID userId) {

    String key = "lock:show:" + showId + ":seat:" + seatId;

    String script =
        "if redis.call('get', KEYS[1]) == ARGV[1] then "
            + "return redis.call('del', KEYS[1]) else return 0 end";

    redisTemplate.execute(
        new DefaultRedisScript<>(script, Long.class), List.of(key), userId.toString());
  }
}
