package com.ayurveda.questionnaire.repository;

import com.ayurveda.questionnaire.model.PatientProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PatientProfileRepository extends MongoRepository<PatientProfile, String> {
    Optional<PatientProfile> findByFirebaseUserId(String firebaseUserId);
    List<PatientProfile> findByPrakriti(String prakriti);
}
