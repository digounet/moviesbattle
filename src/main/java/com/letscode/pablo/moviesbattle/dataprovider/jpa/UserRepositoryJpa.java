package com.letscode.pablo.moviesbattle.dataprovider.jpa;

import com.letscode.pablo.moviesbattle.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends CrudRepository<User, Integer> {
    Optional<User> getByUsername(String username);

    @Query(value = "SELECT * FROM User u WHERE u.total_games > 0 ORDER BY u.total_games DESC, u.total_hits DESC, u.total_mistakes ASC LIMIT :limitno", nativeQuery = true)
    List<User> loadRanking(@Param("limitno") int limitno);
}
