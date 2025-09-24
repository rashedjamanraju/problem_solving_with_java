// Progressive Hint Engine - Never reveals full solutions
import java.util.*;

public class HintEngine {
    private Map<String, List<String>> hintDatabase;
    private Map<String, Integer> userHintLevels;
    private TranslationService translator;
    
    public HintEngine() {
        this.hintDatabase = new HashMap<>();
        this.userHintLevels = new HashMap<>();
        this.translator = new TranslationService();
        initializeHintDatabase();
    }
    
    private void initializeHintDatabase() {
        // Array problems hints
        List<String> arrayHints = Arrays.asList(
            "What information do you need to track while iterating through the array?",
            "Think about what happens when you compare each element with others.",
            "Consider using variables to store important values as you process elements.",
            "What pattern emerges when you look at successful examples step by step?",
            "How might you break this problem into smaller, manageable parts?"
        );
        hintDatabase.put("array", arrayHints);
        
        // Mathematical calculation hints
        List<String> mathHints = Arrays.asList(
            "What mathematical relationship exists between the given numbers?",
            "Can you identify a formula or pattern that applies here?",
            "Think about the order of operations - what should be calculated first?",
            "Consider edge cases - what happens with special values?",
            "What intermediate steps might help you reach the final answer?"
        );
        hintDatabase.put("math", mathHints);
        
        // String processing hints
        List<String> stringHints = Arrays.asList(
            "What properties of strings are important for this problem?",
            "Think about how you can examine each character systematically.",
            "Consider what happens when you process the string from different directions.",
            "What conditions would make this string special or meet the requirements?",
            "How can you efficiently check the characteristics you need?"
        );
        hintDatabase.put("string", stringHints);
        
        // Loop and iteration hints
        List<String> loopHints = Arrays.asList(
            "What should be your loop condition to avoid infinite loops?",
            "What variables need to be updated in each iteration?",
            "Think about your starting values - are they appropriate?",
            "What happens in the first and last iterations of your loop?",
            "Can you trace through a small example step by step?"
        );
        hintDatabase.put("loop", loopHints);
        
        // Conditional logic hints
        List<String> conditionalHints = Arrays.asList(
            "What are all the possible cases you need to handle?",
            "Think about the logical operators - AND, OR, NOT - which applies here?",
            "What conditions would make the result true vs false?",
            "Are there any boundary conditions that need special handling?",
            "Can you simplify complex conditions into smaller, clearer parts?"
        );
        hintDatabase.put("conditional", conditionalHints);
    }
    
    /**
     * Start an interactive hint session for a problem
     */
    public void startHintSession(String userId, ProblemContext context, UserProfile profile) {
        Scanner scanner = new Scanner(System.in);
        String sessionId = userId + "_" + System.currentTimeMillis();
        userHintLevels.put(sessionId, 0);
        
        System.out.println("\n=== Problem-Solving Session ===");
        System.out.println("I'll help you think through this problem with guiding questions.");
        System.out.println("Remember: The goal is to develop your problem-solving skills!");
        
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Get a hint");
            System.out.println("2. Share your approach");
            System.out.println("3. Ask a specific question");
            System.out.println("4. Get analogy/example");
            System.out.println("5. Finish session");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    provideHint(sessionId, context, profile);
                    break;
                case 2:
                    discussApproach(scanner, context, profile);
                    break;
                case 3:
                    answerSpecificQuestion(scanner, context, profile);
                    break;
                case 4:
                    provideAnalogy(context, profile);
                    break;
                case 5:
                    endSession(sessionId, userId);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void provideHint(String sessionId, ProblemContext context, UserProfile profile) {
        int currentLevel = userHintLevels.get(sessionId);
        String problemType = context.getProblemType();
        
        List<String> hints = hintDatabase.getOrDefault(problemType, getGenericHints());
        
        if (currentLevel >= hints.size()) {
            System.out.println("You've used many hints! Try to synthesize what you've learned.");
            System.out.println("Reflect on: What patterns do you see? What approach seems most promising?");
            return;
        }
        
        String hint = hints.get(currentLevel);
        
        // Translate hint if needed
        if (!profile.getPreferredLanguage().equals("English")) {
            hint = translator.translate(hint, profile.getPreferredLanguage());
        }
        
        System.out.println("\n🤔 Think about this: " + hint);
        System.out.println("Take some time to consider this question before asking for more help.");
        
        userHintLevels.put(sessionId, currentLevel + 1);
        
        // Encourage reflection
        System.out.println("\nAfter thinking about this hint, what ideas come to mind?");
    }
    
    private void discussApproach(Scanner scanner, ProblemContext context, UserProfile profile) {
        System.out.println("\nGreat! Tell me about your approach or what you're thinking:");
        String userApproach = scanner.nextLine();
        
        System.out.println("\nInteresting approach! Let me ask you some questions:");
        
        // Provide Socratic questioning based on their approach
        if (userApproach.toLowerCase().contains("loop")) {
            System.out.println("🔍 You mentioned loops. What will you be counting or tracking in each iteration?");
        } else if (userApproach.toLowerCase().contains("array")) {
            System.out.println("🔍 With arrays, think about: Do you need to compare elements? Track positions? Store values?");
        } else if (userApproach.toLowerCase().contains("condition")) {
            System.out.println("🔍 For conditions, consider: What makes a case true vs false? Any edge cases?");
        } else {
            System.out.println("🔍 That's a good start! What would be the first concrete step in your approach?");
            System.out.println("🔍 Can you walk through your method with a simple example?");
        }
        
        System.out.println("\nDoes this help clarify your thinking? What seems most challenging?");
    }
    
    private void answerSpecificQuestion(Scanner scanner, ProblemContext context, UserProfile profile) {
        System.out.println("\nWhat specific aspect would you like guidance on?");
        String question = scanner.nextLine();
        
        System.out.println("\nLet me help you think through that:");
        
        // Provide conceptual guidance without giving away the solution
        if (question.toLowerCase().contains("how to start") || question.toLowerCase().contains("beginning")) {
            System.out.println("🎯 Great question! Think about:");
            System.out.println("   • What data do you have available?");
            System.out.println("   • What is the desired end result?");
            System.out.println("   • What's the simplest case you could handle first?");
        } else if (question.toLowerCase().contains("algorithm") || question.toLowerCase().contains("logic")) {
            System.out.println("🎯 For the logic, consider:");
            System.out.println("   • Can you solve this manually with a small example?");
            System.out.println("   • What steps did your mind take?");
            System.out.println("   • Which of those steps could be repeated/automated?");
        } else {
            System.out.println("🎯 That's a thoughtful question! Instead of giving you the answer,");
            System.out.println("   let me ask: What have you tried so far?");
            System.out.println("   What part feels unclear or challenging?");
        }
    }
    
    private void provideAnalogy(ProblemContext context, UserProfile profile) {
        String problemType = context.getProblemType();
        
        System.out.println("\n🌟 Here's an analogy to help you think about this:");
        
        switch (problemType) {
            case "array":
                System.out.println("Think of an array like a row of boxes, each containing something.");
                System.out.println("You can look into each box one by one, compare contents, or remember what you've seen.");
                System.out.println("What would you need to remember as you go from box to box?");
                break;
            case "math":
                System.out.println("Mathematical problems are like cooking recipes.");
                System.out.println("You have ingredients (input values) and need to follow steps to get your dish (result).");
                System.out.println("What's your 'recipe' for transforming the ingredients?");
                break;
            case "string":
                System.out.println("Strings are like examining a book page by page, or letter by letter.");
                System.out.println("You might need to check spelling, count words, or find patterns.");
                System.out.println("What are you 'reading' and what are you looking for?");
                break;
            default:
                System.out.println("Problem-solving is like being a detective.");
                System.out.println("You have clues (the problem description) and need to find the solution.");
                System.out.println("What clues do you have, and what do they tell you?");
        }
    }
    
    private void endSession(String sessionId, String userId) {
        int hintsUsed = userHintLevels.getOrDefault(sessionId, 0);
        System.out.println("\n=== Session Complete ===");
        System.out.println("Hints used: " + hintsUsed);
        System.out.println("Remember: Every hint was designed to make you think, not to give answers!");
        System.out.println("The real learning happens when you struggle with the concepts.");
        
        // Record session data for progress tracking
        userHintLevels.remove(sessionId);
        System.out.println("Keep practicing! Each problem makes you a stronger problem solver.");
    }
    
    private List<String> getGenericHints() {
        return Arrays.asList(
            "What is the core requirement of this problem?",
            "Can you break this problem down into smaller parts?",
            "What approach would you take if you solved this by hand?",
            "What patterns or relationships do you notice in the given examples?",
            "What edge cases or special conditions should you consider?"
        );
    }
}