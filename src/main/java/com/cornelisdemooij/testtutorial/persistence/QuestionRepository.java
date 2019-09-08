package com.cornelisdemooij.testtutorial.persistence;

import com.cornelisdemooij.testtutorial.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    Optional<Question> findById(Long id);
}