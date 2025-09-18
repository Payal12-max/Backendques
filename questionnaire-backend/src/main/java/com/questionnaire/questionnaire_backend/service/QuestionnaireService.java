package com.ayurveda.questionnaire.service;

import com.ayurveda.questionnaire.model.PatientProfile;
import com.ayurveda.questionnaire.model.OnboardingQuestion;
import com.ayurveda.questionnaire.repository.PatientProfileRepository;
import com.ayurveda.questionnaire.repository.OnboardingQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionnaireService {
    
    @Autowired
    private PatientProfileRepository patientProfileRepository;
    
    @Autowired
    private OnboardingQuestionRepository questionRepository;
    
    public List<OnboardingQuestion> getAllOnboardingQuestions() {
        return questionRepository.findAllByOrderByDisplayOrder();
    }
    
    public PatientProfile submitQuestionnaire(String firebaseUserId, Map<String, Object> questionnaireData) {
        
        // Find existing patient or create new one
        PatientProfile patient = patientProfileRepository.findByFirebaseUserId(firebaseUserId)
                .orElse(new PatientProfile(firebaseUserId));
        
        // Update basic info
        if (questionnaireData.containsKey("name")) {
            patient.setName((String) questionnaireData.get("name"));
        }
        if (questionnaireData.containsKey("email")) {
            patient.setEmail((String) questionnaireData.get("email"));
        }
        if (questionnaireData.containsKey("phone")) {
            patient.setPhone((String) questionnaireData.get("phone"));
        }
        if (questionnaireData.containsKey("age")) {
            patient.setAge((Integer) questionnaireData.get("age"));
        }
        if (questionnaireData.containsKey("gender")) {
            patient.setGender((String) questionnaireData.get("gender"));
        }
        
        // Store questionnaire answers
        patient.setQuestionnaireAnswers(questionnaireData);
        
        // Calculate Prakriti
        String prakriti = calculatePrakriti(questionnaireData);
        patient.setPrakriti(prakriti);
        
        // Mark completed
        patient.setQuestionnaireCompleted(true);
        
        // Award points
        patient.setWellnessPoints(100);
        
        // Award badge
        List<String> badges = patient.getArogyaBadges();
        if (!badges.contains("QUESTIONNAIRE_MASTER")) {
            badges.add("QUESTIONNAIRE_MASTER");
        }
        patient.setArogyaBadges(badges);
        
        return patientProfileRepository.save(patient);
    }
    
    private String calculatePrakriti(Map<String, Object> answers) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("VATA", 0);
        scores.put("PITTA", 0);
        scores.put("KAPHA", 0);
        
        // Body Type
        String bodyType = (String) answers.get("bodyType");
        if ("Thin/Lean".equals(bodyType)) {
            scores.put("VATA", scores.get("VATA") + 3);
        } else if ("Medium Build".equals(bodyType)) {
            scores.put("PITTA", scores.get("PITTA") + 3);
        } else if ("Heavy/Stocky".equals(bodyType)) {
            scores.put("KAPHA", scores.get("KAPHA") + 3);
        }
        
        // Digestion
        String digestion = (String) answers.get("digestion");
        if ("Irregular/Variable".equals(digestion)) {
            scores.put("VATA", scores.get("VATA") + 2);
        } else if ("Strong/Fast".equals(digestion)) {
            scores.put("PITTA", scores.get("PITTA") + 2);
        } else if ("Slow/Steady".equals(digestion)) {
            scores.put("KAPHA", scores.get("KAPHA") + 2);
        }
        
        // Sleep
        String sleep = (String) answers.get("sleepPattern");
        if ("Light Sleeper".equals(sleep)) {
            scores.put("VATA", scores.get("VATA") + 2);
        } else if ("Moderate Sleep".equals(sleep)) {
            scores.put("PITTA", scores.get("PITTA") + 2);
        } else if ("Deep Sleeper".equals(sleep)) {
            scores.put("KAPHA", scores.get("KAPHA") + 2);
        }
        
        // Find highest score
        String dominantPrakriti = scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
                
        return dominantPrakriti;
    }
    
    public Optional<PatientProfile> getPatientProfileByFirebaseUserId(String firebaseUserId) {
        return patientProfileRepository.findByFirebaseUserId(firebaseUserId);
    }
}
