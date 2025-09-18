package com.ayurveda.questionnaire.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "onboarding_questions")
public class OnboardingQuestion {
    @Id
    private String id;
    
    private String questionText;
    private String questionType; // MULTIPLE_CHOICE, TEXT
    private String category; // PRAKRITI, HEALTH_HISTORY, LIFESTYLE, DIET
    private List<String> options; // For multiple choice
    private Integer displayOrder;
    private boolean isRequired = true;

    // Constructors
    public OnboardingQuestion() {}
    
    public OnboardingQuestion(String questionText, String questionType, String category) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.category = category;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    
    public String getQuestionType() { return questionType; }
    public void setQuestionType(String questionType) { this.questionType = questionType; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public boolean isRequired() { return isRequired; }
    public void setRequired(boolean required) { isRequired = required; }
}
