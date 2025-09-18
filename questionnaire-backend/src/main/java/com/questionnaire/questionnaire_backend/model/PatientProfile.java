package com.ayurveda.questionnaire.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "patient_profiles")
public class PatientProfile {
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String firebaseUserId; // From Firebase Auth
    
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private String gender;
    
    // Questionnaire Data
    private Map<String, Object> questionnaireAnswers;
    private String prakriti; // VATA, PITTA, KAPHA
    private boolean questionnaireCompleted = false;
    
    // Gamification
    private Integer wellnessPoints = 50;
    private List<String> arogyaBadges = new ArrayList<>();
    
    @CreatedDate
    private LocalDateTime createdAt;

    // Constructors
    public PatientProfile() {}
    
    public PatientProfile(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
        this.wellnessPoints = 50;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getFirebaseUserId() { return firebaseUserId; }
    public void setFirebaseUserId(String firebaseUserId) { this.firebaseUserId = firebaseUserId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public Map<String, Object> getQuestionnaireAnswers() { return questionnaireAnswers; }
    public void setQuestionnaireAnswers(Map<String, Object> questionnaireAnswers) { 
        this.questionnaireAnswers = questionnaireAnswers; 
    }
    
    public String getPrakriti() { return prakriti; }
    public void setPrakriti(String prakriti) { this.prakriti = prakriti; }
    
    public boolean isQuestionnaireCompleted() { return questionnaireCompleted; }
    public void setQuestionnaireCompleted(boolean questionnaireCompleted) { 
        this.questionnaireCompleted = questionnaireCompleted; 
    }
    
    public Integer getWellnessPoints() { return wellnessPoints; }
    public void setWellnessPoints(Integer wellnessPoints) { this.wellnessPoints = wellnessPoints; }
    
    public List<String> getArogyaBadges() { return arogyaBadges; }
    public void setArogyaBadges(List<String> arogyaBadges) { this.arogyaBadges = arogyaBadges; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
