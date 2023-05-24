package com.uet.quangnv.repository;

import com.uet.quangnv.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
    @Query(value = "update from question set status = 1 where id in ?", nativeQuery = true)
    @Modifying
    void censorshipListQuestion(List<Long> questionIds);
}
