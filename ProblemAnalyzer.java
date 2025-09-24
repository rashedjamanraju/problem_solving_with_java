// Problem Analyzer - Analyzes problem statements to determine type and context
import java.util.*;
import java.util.regex.Pattern;

public class ProblemAnalyzer {
    private Map<String, List<String>> keywordMappings;
    
    public ProblemAnalyzer() {
        initializeKeywordMappings();
    }
    
    private void initializeKeywordMappings() {
        keywordMappings = new HashMap<>();
        
        // Array-related keywords
        keywordMappings.put("array", Arrays.asList(
            "array", "elements", "index", "iterate", "traverse", "sort", 
            "search", "maximum", "minimum", "sum", "average", "largest", "smallest"
        ));
        
        // Math-related keywords
        keywordMappings.put("math", Arrays.asList(
            "calculate", "compute", "formula", "equation", "multiplication", 
            "division", "addition", "subtraction", "factorial", "prime", "fibonacci"
        ));
        
        // String-related keywords
        keywordMappings.put("string", Arrays.asList(
            "string", "character", "word", "text", "palindrome", "substring", 
            "length", "reverse", "uppercase", "lowercase", "vowel", "consonant"
        ));
        
        // Loop-related keywords
        keywordMappings.put("loop", Arrays.asList(
            "repeat", "iterate", "for", "while", "loop", "times", "until", 
            "sequence", "series", "pattern"
        ));
        
        // Conditional logic keywords
        keywordMappings.put("conditional", Arrays.asList(
            "if", "condition", "check", "compare", "greater", "less", "equal", 
            "odd", "even", "positive", "negative", "true", "false"
        ));
    }
    
    /**
     * Analyze problem statement and determine context
     */
    public ProblemContext analyzeProblem(String problemStatement) {
        String problemType = determineProblemType(problemStatement);
        int complexityLevel = estimateComplexity(problemStatement);
        List<String> suggestedApproaches = getSuggestedApproaches(problemType);
        Map<String, String> keyInsights = extractKeyInsights(problemStatement);
        
        return new ProblemContext(
            problemStatement, 
            problemType, 
            complexityLevel, 
            suggestedApproaches, 
            keyInsights
        );
    }
    
    private String determineProblemType(String statement) {
        String lowerStatement = statement.toLowerCase();
        Map<String, Integer> typeScores = new HashMap<>();
        
        // Score each type based on keyword matches
        for (Map.Entry<String, List<String>> entry : keywordMappings.entrySet()) {
            String type = entry.getKey();
            List<String> keywords = entry.getValue();
            
            int score = 0;
            for (String keyword : keywords) {
                if (lowerStatement.contains(keyword)) {
                    score++;
                }
            }
            typeScores.put(type, score);
        }
        
        // Return type with highest score, default to "general"
        return typeScores.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .filter(entry -> entry.getValue() > 0)
            .map(Map.Entry::getKey)
            .orElse("general");
    }
    
    private int estimateComplexity(String statement) {
        int complexity = 1; // Base complexity
        String lower = statement.toLowerCase();
        
        // Increase complexity based on certain indicators
        if (lower.contains("nested") || lower.contains("two-dimensional")) complexity += 2;
        if (lower.contains("optimize") || lower.contains("efficient")) complexity += 2;
        if (lower.contains("multiple") || lower.contains("several")) complexity += 1;
        if (lower.contains("recursive") || lower.contains("recursion")) complexity += 3;
        if (statement.length() > 500) complexity += 1; // Longer descriptions often mean more complex
        
        // Count number of constraints/conditions
        long conditionCount = Pattern.compile("if|when|where|such that|given that")
            .matcher(lower)
            .results()
            .count();
        complexity += (int) conditionCount;
        
        return Math.min(complexity, 5); // Cap at 5
    }
    
    private List<String> getSuggestedApproaches(String problemType) {
        Map<String, List<String>> approachMap = new HashMap<>();
        
        approachMap.put("array", Arrays.asList(
            "Iteration and tracking",
            "Two-pointer technique", 
            "Divide and conquer",
            "Dynamic programming (if applicable)"
        ));
        
        approachMap.put("math", Arrays.asList(
            "Direct formula application",
            "Step-by-step calculation",
            "Pattern recognition",
            "Mathematical induction"
        ));
        
        approachMap.put("string", Arrays.asList(
            "Character-by-character processing",
            "Pattern matching",
            "String manipulation",
            "Regular expressions"
        ));
        
        approachMap.put("loop", Arrays.asList(
            "For loop with counter",
            "While loop with condition",
            "Nested loops for complex patterns",
            "Loop optimization"
        ));
        
        approachMap.put("conditional", Arrays.asList(
            "If-else chains",
            "Switch statements",
            "Boolean logic",
            "Guard clauses"
        ));
        
        return approachMap.getOrDefault(problemType, Arrays.asList(
            "Break down into sub-problems",
            "Identify patterns",
            "Use appropriate data structures",
            "Consider edge cases"
        ));
    }
    
    private Map<String, String> extractKeyInsights(String statement) {
        Map<String, String> insights = new HashMap<>();
        String lower = statement.toLowerCase();
        
        // Extract input/output format information
        if (statement.contains("Input:") && statement.contains("Output:")) {
            insights.put("has_io_format", "true");
        }
        
        // Check for examples
        if (lower.contains("example") || lower.contains("sample")) {
            insights.put("has_examples", "true");
        }
        
        // Check for constraints
        if (lower.contains("constraint") || lower.contains("limit") || 
            statement.matches(".*\\d+\\s*<=.*<=\\s*\\d+.*")) {
            insights.put("has_constraints", "true");
        }
        
        // Detect time complexity hints
        if (lower.contains("efficient") || lower.contains("optimal") || lower.contains("fast")) {
            insights.put("efficiency_required", "true");
        }
        
        // Detect mathematical nature
        if (statement.matches(".*\\b\\d+.*\\b.*") || lower.contains("calculate") || 
            lower.contains("formula")) {
            insights.put("mathematical", "true");
        }
        
        return insights;
    }
    
    /**
     * Parse problem from existing Java solution files
     */
    public ProblemContext analyzeFromJavaFile(String fileName, String fileContent) {
        // Extract problem statement from comments
        String problemStatement = extractProblemFromComments(fileContent);
        if (problemStatement.isEmpty()) {
            problemStatement = "Problem: " + fileName.replaceAll("\\d+", "").replace(".java", "");
        }
        
        ProblemContext context = analyzeProblem(problemStatement);
        
        // Add file-specific insights
        Map<String, String> insights = new HashMap<>(context.getKeyInsights());
        insights.put("source_file", fileName);
        insights.put("has_solution", "true");
        
        return new ProblemContext(
            problemStatement,
            context.getProblemType(),
            context.getComplexityLevel(),
            context.getSuggestedApproaches(),
            insights
        );
    }
    
    private String extractProblemFromComments(String fileContent) {
        // Look for comment lines that contain problem description
        String[] lines = fileContent.split("\n");
        StringBuilder problemText = new StringBuilder();
        
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("//") && line.length() > 2) {
                String comment = line.substring(2).trim();
                if (comment.startsWith("Problem:")) {
                    problemText.append(comment).append(" ");
                }
            }
        }
        
        return problemText.toString().trim();
    }
}

class ProblemContext {
    private String problemStatement;
    private String problemType;
    private int complexityLevel;
    private List<String> suggestedApproaches;
    private Map<String, String> keyInsights;
    
    public ProblemContext(String problemStatement, String problemType, int complexityLevel, 
                         List<String> suggestedApproaches, Map<String, String> keyInsights) {
        this.problemStatement = problemStatement;
        this.problemType = problemType;
        this.complexityLevel = complexityLevel;
        this.suggestedApproaches = new ArrayList<>(suggestedApproaches);
        this.keyInsights = new HashMap<>(keyInsights);
    }
    
    // Getters
    public String getProblemStatement() { return problemStatement; }
    public String getProblemType() { return problemType; }
    public int getComplexityLevel() { return complexityLevel; }
    public List<String> getSuggestedApproaches() { return new ArrayList<>(suggestedApproaches); }
    public Map<String, String> getKeyInsights() { return new HashMap<>(keyInsights); }
    
    public boolean hasInsight(String key) {
        return keyInsights.containsKey(key);
    }
    
    public String getInsight(String key) {
        return keyInsights.get(key);
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProblemContext{type='%s', complexity=%d, approaches=%s}", 
            problemType, complexityLevel, suggestedApproaches
        );
    }
}