package com.qcard.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisTokenRepository extends CrudRepository<RefreshToken, String> {
}
