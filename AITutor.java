// AI Tutor - Main Application Class
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class AITutor {
    private ProblemSolvingAgent agent;
    private Map<String, String> problemDatabase;
    private Scanner scanner;
    
    public AITutor() {
        this.agent = new ProblemSolvingAgent();
        this.problemDatabase = new HashMap<>();
        this.scanner = new Scanner(System.in);
        loadProblemsFromRepository();
    }
    
    /**
     * Load existing problems from Java files in the repository
     */
    private void loadProblemsFromRepository() {
        try {
            Path currentDir = Paths.get(".");
            Files.walk(currentDir, 1)
                .filter(path -> path.toString().endsWith(".java"))
                .filter(path -> !path.toString().contains("Agent"))
                .filter(path -> !path.toString().contains("Service"))
                .filter(path -> !path.toString().contains("Engine"))
                .filter(path -> !path.toString().contains("Tracker"))
                .filter(path -> !path.toString().contains("Analyzer"))
                .filter(path -> !path.toString().contains("Manager"))
                .filter(path -> !path.toString().contains("AITutor"))
                .forEach(this::loadProblemFromFile);
                
            System.out.println("Loaded " + problemDatabase.size() + " problems from repository");
        } catch (IOException e) {
            System.err.println("Error loading problems: " + e.getMessage());
        }
    }
    
    private void loadProblemFromFile(Path filePath) {
        try {
            String fileName = filePath.getFileName().toString();
            String content = Files.readString(filePath);
            
            // Extract problem title from comment or filename
            String problemTitle = extractProblemTitle(content, fileName);
            problemDatabase.put(problemTitle, content);
            
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
        }
    }
    
    private String extractProblemTitle(String content, String fileName) {
        // Look for problem title in comments
        String[] lines = content.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("// Problem:")) {
                return line.substring(11).trim();
            }
        }
        
        // Fallback to filename-based title
        return fileName.replaceAll("\\d+", "").replace(".java", "").replaceAll("([A-Z])", " $1").trim();
    }
    
    /**
     * Main application loop
     */
    public void start() {
        printWelcomeMessage();
        
        while (true) {
            printMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    browseProblemsCatalog();
                    break;
                case 2:
                    customProblemSession();
                    break;
                case 3:
                    tutorialMode();
                    break;
                case 4:
                    configureSystem();
                    break;
                case 5:
                    viewSystemInfo();
                    break;
                case 6:
                    System.out.println("Thank you for using AI Tutor! Keep learning and growing! 🚀");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
            }
        }
    }
    
    private void printWelcomeMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🤖 Welcome to AI Problem-Solving Tutor 🤖");
        System.out.println("=".repeat(60));
        System.out.println("I'm your AI tutor designed to help you learn problem-solving");
        System.out.println("through guided hints, translation, and personalized support!");
        System.out.println();
        System.out.println("🎯 Key Features:");
        System.out.println("  • Progressive hints that encourage critical thinking");
        System.out.println("  • Problem translation to Bangla and other languages");
        System.out.println("  • Personalized learning based on your progress");
        System.out.println("  • Never reveals solutions - helps you discover them!");
        System.out.println("=".repeat(60));
    }
    
    private void printMainMenu() {
        System.out.println("\n📚 What would you like to do today?");
        System.out.println("1. Browse Problems Catalog");
        System.out.println("2. Work on Custom Problem");
        System.out.println("3. Tutorial Mode (Learn how to use AI Tutor)");
        System.out.println("4. Configure Settings");
        System.out.println("5. View System Information");
        System.out.println("6. Exit");
        System.out.print("\nChoose an option (1-6): ");
    }
    
    private int getMenuChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // consume invalid input
            return -1;
        }
    }
    
    private void browseProblemsCatalog() {
        System.out.println("\n📋 Available Problems from Repository:");
        System.out.println("-".repeat(50));
        
        List<String> problems = new ArrayList<>(problemDatabase.keySet());
        problems.sort(String::compareToIgnoreCase);
        
        for (int i = 0; i < problems.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, problems.get(i));
        }
        
        System.out.println("-".repeat(50));
        System.out.print("Select a problem number (or 0 to go back): ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 0) return;
            
            if (choice > 0 && choice <= problems.size()) {
                String selectedProblem = problems.get(choice - 1);
                System.out.println("\n🎯 You selected: " + selectedProblem);
                
                // Get user ID for session
                System.out.print("Enter your name/ID for progress tracking: ");
                String userId = scanner.nextLine();
                
                startProblemSession(selectedProblem, userId);
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void customProblemSession() {
        System.out.println("\n✏️ Custom Problem Session");
        System.out.println("Enter your problem statement (or paste it here):");
        System.out.println("(Type 'DONE' on a new line when finished)");
        
        StringBuilder problemText = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("DONE")) {
            problemText.append(line).append("\n");
        }
        
        if (problemText.length() == 0) {
            System.out.println("No problem statement entered. Returning to menu.");
            return;
        }
        
        System.out.print("Enter your name/ID for progress tracking: ");
        String userId = scanner.nextLine();
        
        // Create a custom problem session
        ProblemAnalyzer analyzer = new ProblemAnalyzer();
        ProblemContext context = analyzer.analyzeProblem(problemText.toString());
        
        System.out.println("\n🔍 Problem Analysis:");
        System.out.println("  Type: " + context.getProblemType());
        System.out.println("  Complexity Level: " + context.getComplexityLevel() + "/5");
        System.out.println("  Suggested Approaches: " + String.join(", ", context.getSuggestedApproaches()));
        
        // Start hint session
        UserProgressTracker tracker = new UserProgressTracker();
        UserProfile profile = tracker.getUserProfile(userId);
        HintEngine hintEngine = new HintEngine();
        
        hintEngine.startHintSession(userId, context, profile);
    }
    
    private void startProblemSession(String problemTitle, String userId) {
        System.out.println("\n🚀 Starting problem session: " + problemTitle);
        System.out.println("Remember: I'm here to guide you, not to give you answers!");
        
        // Create problem context based on the selected problem
        String problemContent = problemDatabase.get(problemTitle);
        ProblemAnalyzer analyzer = new ProblemAnalyzer();
        ProblemContext context = analyzer.analyzeFromJavaFile(problemTitle + ".java", problemContent);
        
        System.out.println("\n📊 Problem Analysis:");
        System.out.println("  Category: " + context.getProblemType());
        System.out.println("  Difficulty: " + "★".repeat(context.getComplexityLevel()) + 
                          "☆".repeat(5 - context.getComplexityLevel()) + " (" + context.getComplexityLevel() + "/5)");
        
        // Start interactive learning session
        UserProgressTracker tracker = new UserProgressTracker();
        UserProfile profile = tracker.getUserProfile(userId);
        HintEngine hintEngine = new HintEngine();
        
        hintEngine.startHintSession(userId, context, profile);
        
        // Record the attempt
        tracker.recordProblemAttempt(userId, context.getProblemType(), 0, false);
    }
    
    private void tutorialMode() {
        System.out.println("\n🎓 AI Tutor Tutorial Mode");
        System.out.println("=".repeat(40));
        
        System.out.println("\n📖 How AI Tutor Works:");
        System.out.println("1. 🌍 Translation: Problems can be translated to Bangla and other languages");
        System.out.println("2. 🔍 Smart Analysis: I analyze problems to understand their type and complexity");
        System.out.println("3. 💡 Progressive Hints: I provide guided questions and conceptual nudges");
        System.out.println("4. 🚫 No Spoilers: I never give away solutions - you discover them!");
        System.out.println("5. 📈 Progress Tracking: Your learning journey is tracked and personalized");
        
        System.out.println("\n🎯 Hint Types I Provide:");
        System.out.println("• Guiding Questions: \"What information do you need to track?\"");
        System.out.println("• Analogies: \"Think of an array like a row of boxes...\"");
        System.out.println("• Conceptual Nudges: \"Consider what happens in each iteration...\"");
        System.out.println("• Reflection Prompts: \"What patterns do you notice?\"");
        
        System.out.println("\n🌟 Learning Philosophy:");
        System.out.println("The goal is to develop YOUR problem-solving skills!");
        System.out.println("Struggle and confusion are part of learning - embrace them!");
        System.out.println("Each hint is designed to make you think, not to make things easy.");
        
        System.out.println("\n🚀 Ready to try? Let's go back to the main menu!");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private void configureSystem() {
        ConfigManager config = new ConfigManager();
        
        System.out.println("\n⚙️ System Configuration");
        config.printCurrentConfiguration();
        
        System.out.println("\nConfiguration Options:");
        System.out.println("1. Change default language");
        System.out.println("2. Adjust hint difficulty level");
        System.out.println("3. Enable/disable translation");
        System.out.println("4. Reset to defaults");
        System.out.println("5. Back to main menu");
        
        System.out.print("Choose an option (1-5): ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    changeDefaultLanguage(config);
                    break;
                case 2:
                    adjustHintLevel(config);
                    break;
                case 3:
                    toggleTranslation(config);
                    break;
                case 4:
                    config.resetToDefaults();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void changeDefaultLanguage(ConfigManager config) {
        List<String> languages = config.getSupportedLanguages();
        System.out.println("\nSupported Languages:");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println((i + 1) + ". " + languages.get(i));
        }
        
        System.out.print("Select language number: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice > 0 && choice <= languages.size()) {
                config.setDefaultLanguage(languages.get(choice - 1));
                System.out.println("Default language changed to: " + languages.get(choice - 1));
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid selection.");
        }
    }
    
    private void adjustHintLevel(ConfigManager config) {
        System.out.println("\nHint Levels:");
        System.out.println("1. Minimal hints (for advanced learners)");
        System.out.println("2. Light guidance");
        System.out.println("3. Balanced support (recommended)");
        System.out.println("4. More detailed guidance");
        System.out.println("5. Maximum support (for beginners)");
        
        System.out.print("Select level (1-5): ");
        try {
            int level = scanner.nextInt();
            scanner.nextLine();
            
            if (level >= 1 && level <= 5) {
                config.setDefaultHintLevel(level);
                System.out.println("Hint level set to: " + level);
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid selection.");
        }
    }
    
    private void toggleTranslation(ConfigManager config) {
        boolean current = config.isTranslationEnabled();
        config.setTranslationEnabled(!current);
        System.out.println("Translation " + (current ? "disabled" : "enabled") + ".");
    }
    
    private void viewSystemInfo() {
        System.out.println("\n📊 AI Tutor System Information");
        System.out.println("=".repeat(40));
        System.out.println("Version: 1.0.0");
        System.out.println("Problems Loaded: " + problemDatabase.size());
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        ConfigManager config = new ConfigManager();
        System.out.println("Translation Enabled: " + config.isTranslationEnabled());
        System.out.println("Progress Tracking: " + config.isProgressTrackingEnabled());
        System.out.println("Supported Languages: " + config.getSupportedLanguages().size());
        
        System.out.println("\n🎯 System Status: ✅ All systems operational");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
    
    public static void main(String[] args) {
        AITutor tutor = new AITutor();
        tutor.start();
    }
}