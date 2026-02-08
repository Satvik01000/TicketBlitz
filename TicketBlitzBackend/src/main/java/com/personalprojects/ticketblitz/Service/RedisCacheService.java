package com.personalprojects.ticketblitz.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.redis.core.RedisTemplate; // Uses your custom config
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

  private final RedisTemplate<String, String> redisTemplate;

  private static final Duration CACHE_TTL = Duration.ofHours(1);

  public RedisCacheService(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  private String getKey(UUID showId) {
    return "show_booked:" + showId;
  }

  public Set<UUID> getBookedSeatIds(UUID showId) {
    String key = getKey(showId);

    if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
      return null;
    }

    Set<String> members = redisTemplate.opsForSet().members(key);
    if (members == null || members.isEmpty()) {
      return Collections.emptySet();
    }

    return members.stream().map(UUID::fromString).collect(Collectors.toSet());
  }

  public void cacheBookedSeat(UUID showId, UUID seatId) {
    String key = getKey(showId);
    if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
      redisTemplate.opsForSet().add(key, seatId.toString());
      redisTemplate.expire(key, CACHE_TTL);
    }
  }

  public void removeBookedSeat(UUID showId, UUID seatId) {
    String key = getKey(showId);
    if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
      redisTemplate.opsForSet().remove(key, seatId.toString());
    }
  }

  public void populateCache(UUID showId, Set<UUID> bookedSeatIds) {
    String key = getKey(showId);
    redisTemplate.delete(key);

    if (bookedSeatIds.isEmpty()) {
      return;
    }

    String[] ids = bookedSeatIds.stream().map(UUID::toString).toArray(String[]::new);

    redisTemplate.opsForSet().add(key, ids);
    redisTemplate.expire(key, CACHE_TTL);
  }
}
