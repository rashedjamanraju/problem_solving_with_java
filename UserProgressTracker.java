// User Progress Tracking System
import java.util.*;

public class UserProgressTracker {
    private Map<String, UserProfile> userProfiles;
    
    public UserProgressTracker() {
        this.userProfiles = new HashMap<>();
    }
    
    /**
     * Get or create user profile
     */
    public UserProfile getUserProfile(String userId) {
        return userProfiles.computeIfAbsent(userId, k -> new UserProfile(userId));
    }
    
    /**
     * Update user profile after a session
     */
    public void updateUserProfile(String userId, UserProfile profile) {
        userProfiles.put(userId, profile);
    }
    
    /**
     * Record problem attempt
     */
    public void recordProblemAttempt(String userId, String problemType, int hintsUsed, boolean solved) {
        UserProfile profile = getUserProfile(userId);
        profile.incrementProblemsAttempted();
        profile.addHintsToHistory(hintsUsed);
        profile.updateTopicExperience(problemType);
        
        if (solved) {
            profile.incrementProblemsSolved();
            profile.incrementLearningStreak();
        } else {
            profile.resetLearningStreak();
        }
        
        updateUserProfile(userId, profile);
    }
    
    /**
     * Record user feedback on hints
     */
    public void recordHintFeedback(String userId, String hintId, int usefulness, String feedback) {
        UserProfile profile = getUserProfile(userId);
        profile.addHintFeedback(hintId, usefulness, feedback);
        updateUserProfile(userId, profile);
    }
    
    /**
     * Get personalized recommendations for a user
     */
    public List<String> getPersonalizedRecommendations(String userId) {
        UserProfile profile = getUserProfile(userId);
        List<String> recommendations = new ArrayList<>();
        
        // Recommend based on performance and preferences
        if (profile.getAverageHintsUsed() > 4) {
            recommendations.add("Try working through problems step-by-step before asking for hints");
            recommendations.add("Practice with simpler problems to build confidence");
        }
        
        if (profile.getLearningStreak() > 5) {
            recommendations.add("Great streak! Challenge yourself with harder problems");
        }
        
        // Recommend topics based on experience
        Map<String, Integer> topicExperience = profile.getTopicExperience();
        String leastExperiencedTopic = topicExperience.entrySet().stream()
            .min(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("array");
        
        recommendations.add("Consider practicing more " + leastExperiencedTopic + " problems");
        
        return recommendations;
    }
}

class UserProfile {
    private String userId;
    private String preferredLanguage;
    private int hintLevel;
    private String learningStyle;
    private int problemsAttempted;
    private int problemsSolved;
    private List<Integer> hintsUsedHistory;
    private int learningStreak;
    private List<String> favoriteTopics;
    private Map<String, Integer> topicExperience;
    private Map<String, HintFeedback> hintFeedbacks;
    private long joinDate;
    private long lastActive;
    
    public UserProfile(String userId) {
        this.userId = userId;
        this.preferredLanguage = "English";
        this.hintLevel = 3; // Default moderate hint level
        this.learningStyle = "balanced";
        this.problemsAttempted = 0;
        this.problemsSolved = 0;
        this.hintsUsedHistory = new ArrayList<>();
        this.learningStreak = 0;
        this.favoriteTopics = new ArrayList<>();
        this.topicExperience = new HashMap<>();
        this.hintFeedbacks = new HashMap<>();
        this.joinDate = System.currentTimeMillis();
        this.lastActive = System.currentTimeMillis();
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public int getHintLevel() { return hintLevel; }
    public String getLearningStyle() { return learningStyle; }
    public int getProblemsAttempted() { return problemsAttempted; }
    public int getProblemsSolved() { return problemsSolved; }
    public int getLearningStreak() { return learningStreak; }
    public List<String> getFavoriteTopics() { return new ArrayList<>(favoriteTopics); }
    public Map<String, Integer> getTopicExperience() { return new HashMap<>(topicExperience); }
    
    // Setters
    public void setPreferredLanguage(String language) { this.preferredLanguage = language; }
    public void setHintLevel(int level) { this.hintLevel = level; }
    public void setLearningStyle(String style) { this.learningStyle = style; }
    
    // Progress tracking methods
    public void incrementProblemsAttempted() { this.problemsAttempted++; }
    public void incrementProblemsSolved() { this.problemsSolved++; }
    public void incrementLearningStreak() { this.learningStreak++; }
    public void resetLearningStreak() { this.learningStreak = 0; }
    
    public void addHintsToHistory(int hints) {
        this.hintsUsedHistory.add(hints);
        this.lastActive = System.currentTimeMillis();
    }
    
    public double getAverageHintsUsed() {
        if (hintsUsedHistory.isEmpty()) return 0.0;
        return hintsUsedHistory.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
    
    public void updateTopicExperience(String topic) {
        topicExperience.merge(topic, 1, Integer::sum);
        
        // Update favorite topics based on experience
        favoriteTopics.clear();
        topicExperience.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .forEach(entry -> favoriteTopics.add(entry.getKey()));
    }
    
    public void addHintFeedback(String hintId, int usefulness, String feedback) {
        hintFeedbacks.put(hintId, new HintFeedback(usefulness, feedback, System.currentTimeMillis()));
    }
    
    public double getSolveRate() {
        if (problemsAttempted == 0) return 0.0;
        return (double) problemsSolved / problemsAttempted;
    }
    
    public boolean isActiveUser() {
        long oneDayAgo = System.currentTimeMillis() - (24 * 60 * 60 * 1000);
        return lastActive > oneDayAgo;
    }
}

class HintFeedback {
    private int usefulness; // 1-5 scale
    private String feedback;
    private long timestamp;
    
    public HintFeedback(int usefulness, String feedback, long timestamp) {
        this.usefulness = usefulness;
        this.feedback = feedback;
        this.timestamp = timestamp;
    }
    
    // Getters
    public int getUsefulness() { return usefulness; }
    public String getFeedback() { return feedback; }
    public long getTimestamp() { return timestamp; }
}