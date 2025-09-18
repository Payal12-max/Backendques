package com.ayurveda.questionnaire.config;

import com.ayurveda.questionnaire.model.OnboardingQuestion;
import com.ayurveda.questionnaire.repository.OnboardingQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private OnboardingQuestionRepository questionRepository;
    
    @Override
    public void run(String... args) throws Exception {
            if (questionRepository.count() == 0) {
            initializeQuestions();
            System.out.println("✅ Sample questions loaded!");
        }
    }
    
    private void initializeQuestions() {
        List<OnboardingQuestion> questions = Arrays.asList(
            createQuestion("What best describes your body type?", "MULTIPLE_CHOICE", "PRAKRITI",
                Arrays.asList("Thin/Lean", "Medium Build", "Heavy/Stocky"), 1),
            
            createQuestion("How would you describe your digestion?", "MULTIPLE_CHOICE", "PRAKRITI",
                Arrays.asList("Irregular/Variable", "Strong/Fast", "Slow/Steady"), 2),
            
            createQuestion("What is your typical sleep pattern?", "MULTIPLE_CHOICE", "PRAKRITI",
                Arrays.asList("Light Sleeper", "Moderate Sleep", "Deep Sleeper"), 3),
            
            createQuestion("How would you describe your energy levels?", "MULTIPLE_CHOICE", "PRAKRITI",
                Arrays.asList("Variable/Fluctuating", "High/Intense", "Steady/Consistent"), 4),
            
            createQuestion("What is your typical appetite like?", "MULTIPLE_CHOICE", "PRAKRITI",
                Arrays.asList("Irregular", "Strong/Good", "Low but steady"), 5),
            
            createQuestion("How do you typically respond to stress?", "MULTIPLE_CHOICE", "PRAKRITI",
                Arrays.asList("Get anxious/worried", "Get angry/irritated", "Remain calm/withdrawn"), 6),
            
            createQuestion("Do you have any existing health conditions?", "TEXT", "HEALTH_HISTORY",
                null, 7),
            
            createQuestion("Any food allergies or dietary restrictions?", "TEXT", "DIET",
                null, 8)
        );
        
        questionRepository.saveAll(questions);
    }
    
    private OnboardingQuestion createQuestion(String text, String type, String category,
                                            List<String> options, Integer order) {
        OnboardingQuestion question = new OnboardingQuestion(text, type, category);
        question.setOptions(options);
        question.setDisplayOrder(order);
        return question;
    }
}
