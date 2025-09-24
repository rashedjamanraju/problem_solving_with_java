// AI Problem-Solving Agent - Core Framework
import java.util.*;

public class ProblemSolvingAgent {
    private TranslationService translator;
    private HintEngine hintEngine;
    private UserProgressTracker progressTracker;
    private ConfigManager config;
    private ProblemAnalyzer analyzer;
    
    public ProblemSolvingAgent() {
        this.translator = new TranslationService();
        this.hintEngine = new HintEngine();
        this.progressTracker = new UserProgressTracker();
        this.config = new ConfigManager();
        this.analyzer = new ProblemAnalyzer();
    }
    
    /**
     * Main interaction method for users
     */
    public void startSession(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to AI Problem-Solving Tutor!");
        System.out.println("I'm here to help you learn problem-solving through guided hints and translation.");
        
        // Load user preferences
        UserProfile profile = progressTracker.getUserProfile(userId);
        String preferredLanguage = profile.getPreferredLanguage();
        
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Work on a problem");
            System.out.println("2. Configure settings");
            System.out.println("3. View progress");
            System.out.println("4. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    solveProblem(userId, scanner);
                    break;
                case 2:
                    configureSettings(userId, scanner);
                    break;
                case 3:
                    viewProgress(userId);
                    break;
                case 4:
                    System.out.println("Happy learning!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void solveProblem(String userId, Scanner scanner) {
        System.out.println("\nEnter problem statement or problem ID:");
        String problemInput = scanner.nextLine();
        
        UserProfile profile = progressTracker.getUserProfile(userId);
        ProblemContext context = analyzer.analyzeProblem(problemInput);
        
        // Translate if needed
        if (!profile.getPreferredLanguage().equals("English")) {
            String translatedProblem = translator.translate(problemInput, profile.getPreferredLanguage());
            System.out.println("\nTranslated Problem Statement:");
            System.out.println(translatedProblem);
        }
        
        System.out.println("\nOriginal Problem Statement:");
        System.out.println(problemInput);
        
        // Start hint-based learning session
        hintEngine.startHintSession(userId, context, profile);
    }
    
    private void configureSettings(String userId, Scanner scanner) {
        UserProfile profile = progressTracker.getUserProfile(userId);
        
        System.out.println("\nCurrent Settings:");
        System.out.println("Preferred Language: " + profile.getPreferredLanguage());
        System.out.println("Hint Level: " + profile.getHintLevel());
        System.out.println("Learning Style: " + profile.getLearningStyle());
        
        System.out.println("\nChange settings? (y/n)");
        if (scanner.nextLine().toLowerCase().startsWith("y")) {
            System.out.println("Select preferred language:");
            System.out.println("1. English");
            System.out.println("2. Bangla");
            System.out.println("3. Spanish");
            System.out.println("4. French");
            
            int langChoice = scanner.nextInt();
            scanner.nextLine();
            
            String[] languages = {"English", "Bangla", "Spanish", "French"};
            if (langChoice >= 1 && langChoice <= 4) {
                profile.setPreferredLanguage(languages[langChoice - 1]);
            }
            
            System.out.println("Select hint level (1-5, where 1 is minimal hints, 5 is more guidance):");
            int hintLevel = scanner.nextInt();
            scanner.nextLine();
            
            if (hintLevel >= 1 && hintLevel <= 5) {
                profile.setHintLevel(hintLevel);
            }
            
            progressTracker.updateUserProfile(userId, profile);
            System.out.println("Settings updated!");
        }
    }
    
    private void viewProgress(String userId) {
        UserProfile profile = progressTracker.getUserProfile(userId);
        System.out.println("\n=== Your Progress ===");
        System.out.println("Problems Attempted: " + profile.getProblemsAttempted());
        System.out.println("Problems Solved: " + profile.getProblemsSolved());
        System.out.println("Average Hints Used: " + profile.getAverageHintsUsed());
        System.out.println("Learning Streak: " + profile.getLearningStreak());
        System.out.println("Favorite Topics: " + String.join(", ", profile.getFavoriteTopics()));
    }
    
    public static void main(String[] args) {
        ProblemSolvingAgent agent = new ProblemSolvingAgent();
        System.out.println("Enter your user ID (or 'guest' for guest mode):");
        Scanner scanner = new Scanner(System.in);
        String userId = scanner.nextLine();
        agent.startSession(userId);
    }
}