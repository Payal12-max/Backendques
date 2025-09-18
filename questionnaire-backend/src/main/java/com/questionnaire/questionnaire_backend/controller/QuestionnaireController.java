package com.ayurveda.questionnaire.controller;

import com.ayurveda.questionnaire.model.PatientProfile;
import com.ayurveda.questionnaire.model.OnboardingQuestion;
import com.ayurveda.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/questionnaire")
@CrossOrigin(origins = "*")
public class QuestionnaireController {
    
    @Autowired
    private QuestionnaireService questionnaireService;
    
    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "🌿 Questionnaire Backend Working!");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/questions")
    public ResponseEntity<List<OnboardingQuestion>> getOnboardingQuestions() {
        List<OnboardingQuestion> questions = questionnaireService.getAllOnboardingQuestions();
        return ResponseEntity.ok(questions);
    }
    
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitQuestionnaire(@RequestBody Map<String, Object> requestData) {
        try {
            String firebaseUserId = (String) requestData.get("firebaseUserId");
            
            if (firebaseUserId == null || firebaseUserId.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Firebase User ID is required");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            PatientProfile patientProfile = questionnaireService.submitQuestionnaire(firebaseUserId, requestData);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Questionnaire submitted successfully!");
            response.put("patientId", patientProfile.getId());
            response.put("prakriti", patientProfile.getPrakriti());
            response.put("wellnessPoints", patientProfile.getWellnessPoints());
            response.put("arogyaBadges", patientProfile.getArogyaBadges());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @GetMapping("/profile/{firebaseUserId}")
    public ResponseEntity<PatientProfile> getPatientProfile(@PathVariable String firebaseUserId) {
        Optional<PatientProfile> patientProfile = questionnaireService.getPatientProfileByFirebaseUserId(firebaseUserId);
        return patientProfile.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
