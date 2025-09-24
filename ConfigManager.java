// Configuration Manager for AI Tutor System
import java.util.*;
import java.io.*;

public class ConfigManager {
    private Properties config;
    private String configFile;
    
    public ConfigManager() {
        this.configFile = "ai_tutor_config.properties";
        this.config = new Properties();
        loadDefaultConfiguration();
        loadConfigurationFromFile();
    }
    
    private void loadDefaultConfiguration() {
        // Default system configurations
        config.setProperty("default.language", "English");
        config.setProperty("default.hint.level", "3");
        config.setProperty("default.learning.style", "balanced");
        config.setProperty("max.hints.per.session", "10");
        config.setProperty("session.timeout.minutes", "30");
        config.setProperty("translation.enabled", "true");
        config.setProperty("progress.tracking.enabled", "true");
        config.setProperty("feedback.collection.enabled", "true");
        config.setProperty("hint.delay.seconds", "5");
        config.setProperty("adaptive.difficulty.enabled", "true");
        
        // Language preferences
        config.setProperty("supported.languages", "English,Bangla,Spanish,French");
        config.setProperty("primary.language", "English");
        
        // Hint system configuration
        config.setProperty("hint.types", "question,analogy,example,guidance");
        config.setProperty("min.reflection.time", "10");
        config.setProperty("max.session.duration", "60");
        
        // User engagement settings
        config.setProperty("encouragement.enabled", "true");
        config.setProperty("streak.rewards.enabled", "true");
        config.setProperty("difficulty.adaptation.threshold", "3");
    }
    
    private void loadConfigurationFromFile() {
        try (FileInputStream fis = new FileInputStream(configFile)) {
            config.load(fis);
            System.out.println("Configuration loaded from " + configFile);
        } catch (IOException e) {
            System.out.println("No existing configuration file found. Using defaults.");
            saveConfigurationToFile(); // Create default file
        }
    }
    
    private void saveConfigurationToFile() {
        try (FileOutputStream fos = new FileOutputStream(configFile)) {
            config.store(fos, "AI Tutor System Configuration");
            System.out.println("Configuration saved to " + configFile);
        } catch (IOException e) {
            System.err.println("Failed to save configuration: " + e.getMessage());
        }
    }
    
    // Configuration getters
    public String getDefaultLanguage() {
        return config.getProperty("default.language", "English");
    }
    
    public int getDefaultHintLevel() {
        return Integer.parseInt(config.getProperty("default.hint.level", "3"));
    }
    
    public String getDefaultLearningStyle() {
        return config.getProperty("default.learning.style", "balanced");
    }
    
    public int getMaxHintsPerSession() {
        return Integer.parseInt(config.getProperty("max.hints.per.session", "10"));
    }
    
    public int getSessionTimeoutMinutes() {
        return Integer.parseInt(config.getProperty("session.timeout.minutes", "30"));
    }
    
    public boolean isTranslationEnabled() {
        return Boolean.parseBoolean(config.getProperty("translation.enabled", "true"));
    }
    
    public boolean isProgressTrackingEnabled() {
        return Boolean.parseBoolean(config.getProperty("progress.tracking.enabled", "true"));
    }
    
    public boolean isFeedbackCollectionEnabled() {
        return Boolean.parseBoolean(config.getProperty("feedback.collection.enabled", "true"));
    }
    
    public int getHintDelaySeconds() {
        return Integer.parseInt(config.getProperty("hint.delay.seconds", "5"));
    }
    
    public boolean isAdaptiveDifficultyEnabled() {
        return Boolean.parseBoolean(config.getProperty("adaptive.difficulty.enabled", "true"));
    }
    
    public List<String> getSupportedLanguages() {
        String languages = config.getProperty("supported.languages", "English");
        return Arrays.asList(languages.split(","));
    }
    
    public boolean isEncouragementEnabled() {
        return Boolean.parseBoolean(config.getProperty("encouragement.enabled", "true"));
    }
    
    public boolean isStreakRewardsEnabled() {
        return Boolean.parseBoolean(config.getProperty("streak.rewards.enabled", "true"));
    }
    
    public int getDifficultyAdaptationThreshold() {
        return Integer.parseInt(config.getProperty("difficulty.adaptation.threshold", "3"));
    }
    
    public int getMinReflectionTime() {
        return Integer.parseInt(config.getProperty("min.reflection.time", "10"));
    }
    
    public int getMaxSessionDuration() {
        return Integer.parseInt(config.getProperty("max.session.duration", "60"));
    }
    
    // Configuration setters
    public void setDefaultLanguage(String language) {
        config.setProperty("default.language", language);
        saveConfigurationToFile();
    }
    
    public void setDefaultHintLevel(int level) {
        config.setProperty("default.hint.level", String.valueOf(level));
        saveConfigurationToFile();
    }
    
    public void setTranslationEnabled(boolean enabled) {
        config.setProperty("translation.enabled", String.valueOf(enabled));
        saveConfigurationToFile();
    }
    
    public void setProgressTrackingEnabled(boolean enabled) {
        config.setProperty("progress.tracking.enabled", String.valueOf(enabled));
        saveConfigurationToFile();
    }
    
    public void setMaxHintsPerSession(int maxHints) {
        config.setProperty("max.hints.per.session", String.valueOf(maxHints));
        saveConfigurationToFile();
    }
    
    // Custom configuration management
    public String getProperty(String key) {
        return config.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }
    
    public void setProperty(String key, String value) {
        config.setProperty(key, value);
        saveConfigurationToFile();
    }
    
    public void removeProperty(String key) {
        config.remove(key);
        saveConfigurationToFile();
    }
    
    // System management methods
    public void resetToDefaults() {
        config.clear();
        loadDefaultConfiguration();
        saveConfigurationToFile();
        System.out.println("Configuration reset to defaults.");
    }
    
    public void printCurrentConfiguration() {
        System.out.println("\n=== Current AI Tutor Configuration ===");
        
        System.out.println("Language Settings:");
        System.out.println("  Default Language: " + getDefaultLanguage());
        System.out.println("  Translation Enabled: " + isTranslationEnabled());
        System.out.println("  Supported Languages: " + getSupportedLanguages());
        
        System.out.println("\nHint System:");
        System.out.println("  Default Hint Level: " + getDefaultHintLevel());
        System.out.println("  Max Hints per Session: " + getMaxHintsPerSession());
        System.out.println("  Hint Delay: " + getHintDelaySeconds() + " seconds");
        System.out.println("  Min Reflection Time: " + getMinReflectionTime() + " seconds");
        
        System.out.println("\nLearning Features:");
        System.out.println("  Progress Tracking: " + isProgressTrackingEnabled());
        System.out.println("  Adaptive Difficulty: " + isAdaptiveDifficultyEnabled());
        System.out.println("  Encouragement: " + isEncouragementEnabled());
        System.out.println("  Streak Rewards: " + isStreakRewardsEnabled());
        
        System.out.println("\nSession Management:");
        System.out.println("  Session Timeout: " + getSessionTimeoutMinutes() + " minutes");
        System.out.println("  Max Session Duration: " + getMaxSessionDuration() + " minutes");
        
        System.out.println("=====================================");
    }
    
    public Map<String, String> getAllProperties() {
        Map<String, String> allProps = new HashMap<>();
        for (String key : config.stringPropertyNames()) {
            allProps.put(key, config.getProperty(key));
        }
        return allProps;
    }
    
    // Validation methods
    public boolean validateConfiguration() {
        try {
            // Validate numeric properties
            getDefaultHintLevel();
            getMaxHintsPerSession();
            getSessionTimeoutMinutes();
            getHintDelaySeconds();
            getDifficultyAdaptationThreshold();
            getMinReflectionTime();
            getMaxSessionDuration();
            
            // Validate boolean properties
            isTranslationEnabled();
            isProgressTrackingEnabled();
            isFeedbackCollectionEnabled();
            isAdaptiveDifficultyEnabled();
            isEncouragementEnabled();
            isStreakRewardsEnabled();
            
            // Validate that supported languages list is not empty
            if (getSupportedLanguages().isEmpty()) {
                System.err.println("Configuration error: No supported languages defined");
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Configuration validation failed: " + e.getMessage());
            return false;
        }
    }
}