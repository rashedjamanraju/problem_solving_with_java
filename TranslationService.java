// Translation Service for Problem Statements
import java.util.*;

public class TranslationService {
    private Map<String, Map<String, String>> translations;
    
    public TranslationService() {
        translations = new HashMap<>();
        initializeTranslations();
    }
    
    private void initializeTranslations() {
        // Initialize basic translation mappings
        // In a real implementation, this would connect to a translation API
        
        // Common programming terms in Bangla
        Map<String, String> banglaTranslations = new HashMap<>();
        banglaTranslations.put("problem", "সমস্যা");
        banglaTranslations.put("solution", "সমাধান");
        banglaTranslations.put("input", "ইনপুট");
        banglaTranslations.put("output", "আউটপুট");
        banglaTranslations.put("array", "অ্যারে");
        banglaTranslations.put("number", "সংখ্যা");
        banglaTranslations.put("integer", "পূর্ণসংখ্যা");
        banglaTranslations.put("string", "স্ট্রিং");
        banglaTranslations.put("loop", "লুপ");
        banglaTranslations.put("condition", "শর্ত");
        banglaTranslations.put("function", "ফাংশন");
        banglaTranslations.put("variable", "ভেরিয়েবল");
        banglaTranslations.put("algorithm", "অ্যালগোরিদম");
        banglaTranslations.put("example", "উদাহরণ");
        banglaTranslations.put("find", "খুঁজে বের করুন");
        banglaTranslations.put("calculate", "গণনা করুন");
        banglaTranslations.put("print", "প্রিন্ট করুন");
        banglaTranslations.put("sum", "যোগফল");
        banglaTranslations.put("average", "গড়");
        banglaTranslations.put("maximum", "সর্বোচ্চ");
        banglaTranslations.put("minimum", "সর্বনিম্ন");
        
        translations.put("Bangla", banglaTranslations);
        
        // Add basic Spanish translations
        Map<String, String> spanishTranslations = new HashMap<>();
        spanishTranslations.put("problem", "problema");
        spanishTranslations.put("solution", "solución");
        spanishTranslations.put("input", "entrada");
        spanishTranslations.put("output", "salida");
        spanishTranslations.put("array", "arreglo");
        spanishTranslations.put("number", "número");
        spanishTranslations.put("find", "encuentra");
        spanishTranslations.put("calculate", "calcula");
        
        translations.put("Spanish", spanishTranslations);
        
        // Add basic French translations
        Map<String, String> frenchTranslations = new HashMap<>();
        frenchTranslations.put("problem", "problème");
        frenchTranslations.put("solution", "solution");
        frenchTranslations.put("input", "entrée");
        frenchTranslations.put("output", "sortie");
        frenchTranslations.put("array", "tableau");
        frenchTranslations.put("number", "nombre");
        frenchTranslations.put("find", "trouver");
        frenchTranslations.put("calculate", "calculer");
        
        translations.put("French", frenchTranslations);
    }
    
    /**
     * Translate text to target language
     */
    public String translate(String text, String targetLanguage) {
        if (targetLanguage.equals("English") || !translations.containsKey(targetLanguage)) {
            return text;
        }
        
        Map<String, String> targetTranslations = translations.get(targetLanguage);
        String translatedText = text;
        
        // Simple word replacement translation
        for (Map.Entry<String, String> entry : targetTranslations.entrySet()) {
            String englishTerm = entry.getKey();
            String translatedTerm = entry.getValue();
            translatedText = translatedText.replaceAll("(?i)\\b" + englishTerm + "\\b", translatedTerm);
        }
        
        return translatedText;
    }
    
    /**
     * Get available languages
     */
    public Set<String> getAvailableLanguages() {
        Set<String> languages = new HashSet<>(translations.keySet());
        languages.add("English");
        return languages;
    }
    
    /**
     * Add custom translation for a specific problem
     */
    public void addCustomTranslation(String problemId, String language, String translatedText) {
        // This could be extended to store custom translations for specific problems
        System.out.println("Custom translation added for problem: " + problemId + " in " + language);
    }
    
    /**
     * Translate common problem patterns to Bangla
     */
    public String translateProblemPattern(String pattern, String language) {
        if (!language.equals("Bangla")) {
            return pattern;
        }
        
        // Common problem statement patterns in Bangla
        Map<String, String> patterns = new HashMap<>();
        patterns.put("Given an array", "একটি অ্যারে দেওয়া আছে");
        patterns.put("Find the sum", "যোগফল বের করুন");
        patterns.put("Calculate the average", "গড় হিসাব করুন");
        patterns.put("Print the result", "ফলাফল প্রিন্ট করুন");
        patterns.put("Input format", "ইনপুট ফরম্যাট");
        patterns.put("Output format", "আউটপুট ফরম্যাট");
        patterns.put("Example", "উদাহরণ");
        patterns.put("Constraints", "সীমাবদ্ধতা");
        
        String translated = pattern;
        for (Map.Entry<String, String> entry : patterns.entrySet()) {
            translated = translated.replace(entry.getKey(), entry.getValue());
        }
        
        return translated;
    }
}