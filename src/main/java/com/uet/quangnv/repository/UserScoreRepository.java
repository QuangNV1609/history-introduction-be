package com.uet.quangnv.repository;

import com.uet.quangnv.entities.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long>, UserScoreRepositoryCustom {
    @Query(value = "Select * from user_score " +
            "WHERE historical_period = ? and num_of_question = ? " +
            "ORDER BY score DESC, user_score.time_exam ", nativeQuery = true)
    List<UserScore> getTopUserScore(Integer historicalPeriod, Integer numOfQuestion);
}
