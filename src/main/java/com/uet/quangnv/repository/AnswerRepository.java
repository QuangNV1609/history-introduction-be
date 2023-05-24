package com.uet.quangnv.repository;

import com.uet.quangnv.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "DELETE from answer where id in ?", nativeQuery = true)
    @Modifying
    void deleteAllByListIds(List<Long> ids);
}
