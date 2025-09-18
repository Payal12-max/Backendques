package com.ayurveda.questionnaire.repository;

import com.ayurveda.questionnaire.model.OnboardingQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnboardingQuestionRepository extends MongoRepository<OnboardingQuestion, String> {
    List<OnboardingQuestion> findAllByOrderByDisplayOrder();
}
