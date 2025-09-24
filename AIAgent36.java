// Problem: AI Agent Implementation
import java.util.*;
import java.util.regex.Pattern;

public class AIAgent36 {
    private String name;
    private List<String> knowledgeBase;
    private Random random;
    private Map<String, String> responses;
    
    public AIAgent36(String name) {
        this.name = name;
        this.knowledgeBase = new ArrayList<>();
        this.random = new Random();
        this.responses = new HashMap<>();
        initializeKnowledge();
    }
    
    private void initializeKnowledge() {
        // Initialize knowledge base
        knowledgeBase.add("I can help you solve programming problems");
        knowledgeBase.add("I understand basic mathematics and algorithms");
        knowledgeBase.add("I can process natural language queries");
        knowledgeBase.add("I learn from interactions");
        
        // Initialize response patterns
        responses.put("hello", "Hello! I'm " + name + ", your AI assistant. How can I help you today?");
        responses.put("help", "I can help you with programming problems, mathematics, and general questions.");
        responses.put("name", "My name is " + name + ". I'm an AI agent designed to assist with problem solving.");
        responses.put("math", "I can solve mathematical problems. Try asking me to calculate something!");
        responses.put("bye", "Goodbye! It was nice helping you today!");
    }
    
    public String processQuery(String input) {
        input = input.toLowerCase().trim();
        
        // Pattern matching for responses
        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (input.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        
        // Mathematical operations
        if (input.contains("calculate") || input.contains("solve")) {
            return processMathQuery(input);
        }
        
        // Programming help
        if (input.contains("program") || input.contains("code") || input.contains("java")) {
            return "I can help with Java programming! What specific problem are you working on?";
        }
        
        // Default intelligent response
        return generateIntelligentResponse(input);
    }
    
    private String processMathQuery(String input) {
        // Extract numbers from the input
        Pattern pattern = Pattern.compile("\\d+");
        java.util.regex.Matcher matcher = pattern.matcher(input);
        List<Integer> numbers = new ArrayList<>();
        
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        
        if (numbers.size() >= 2) {
            int a = numbers.get(0);
            int b = numbers.get(1);
            
            if (input.contains("add") || input.contains("+")) {
                return String.format("The sum of %d and %d is %d", a, b, a + b);
            } else if (input.contains("multiply") || input.contains("*")) {
                return String.format("The product of %d and %d is %d", a, b, a * b);
            } else if (input.contains("subtract") || input.contains("-")) {
                return String.format("The difference of %d and %d is %d", a, b, a - b);
            } else if (input.contains("divide") || input.contains("/")) {
                if (b != 0) {
                    return String.format("The quotient of %d and %d is %.2f", a, b, (double) a / b);
                } else {
                    return "Cannot divide by zero!";
                }
            }
        }
        
        return "I can help with basic math operations. Please provide two numbers and an operation.";
    }
    
    private String generateIntelligentResponse(String input) {
        String[] intelligentResponses = {
            "That's an interesting question! Let me think about it...",
            "I understand you're asking about: " + input + ". Could you be more specific?",
            "Based on my knowledge, I'd suggest looking into this topic further.",
            "That's a great question! Here's what I know about it...",
            "I'm analyzing your query and forming a response..."
        };
        
        return intelligentResponses[random.nextInt(intelligentResponses.length)];
    }
    
    public void learnFromInteraction(String input, String feedback) {
        if (feedback.toLowerCase().contains("good") || feedback.toLowerCase().contains("correct")) {
            knowledgeBase.add("Learned: " + input + " was handled well");
        }
    }
    
    public void displayKnowledge() {
        System.out.println("\n=== " + name + "'s Knowledge Base ===");
        for (String knowledge : knowledgeBase) {
            System.out.println("- " + knowledge);
        }
    }
    
    public String getName() {
        return name;
    }
    
    public int getKnowledgeSize() {
        return knowledgeBase.size();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AIAgent36 agent = new AIAgent36("AIRA"); // AI Reasoning Assistant
        
        System.out.println("=== AI Agent Interactive Session ===");
        System.out.println("AI Agent '" + agent.getName() + "' is now active!");
        System.out.println("Type 'quit' to exit, 'knowledge' to see my knowledge base\n");
        
        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();
            
            if (input.toLowerCase().equals("quit")) {
                System.out.println("AI Agent: " + agent.processQuery("bye"));
                break;
            }
            
            if (input.toLowerCase().equals("knowledge")) {
                agent.displayKnowledge();
                continue;
            }
            
            String response = agent.processQuery(input);
            System.out.println("AI Agent: " + response);
            
            // Simulate learning
            if (input.length() > 5) { // Only learn from substantial interactions
                agent.learnFromInteraction(input, "processed");
            }
        }
        
        System.out.println("\nSession ended. Final knowledge size: " + agent.getKnowledgeSize());
        sc.close();
    }
}