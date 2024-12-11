package com.example.codeanalysis.service;

import com.example.codeanalysis.model.TestScenario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject; // Assurez-vous d'importer cette bibliothèque
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class CodeAnalysisService {

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    // Récupérer la clé API depuis le fichier properties
    @Value("${spring.ia.openai.api-key}")
    private String apiKey;

    public List<TestScenario> analyzeCode(String code) {
        String prompt = "Analyse ce code et génère des tests unitaires :\n\n" + code;
        List<TestScenario> scenarios = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            // Construire le corps de la requête avec Jackson
            ObjectNode requestBodyJson = objectMapper.createObjectNode();
            requestBodyJson.put("model", "gpt-3.5-turbo");

            ArrayNode messages = objectMapper.createArrayNode();
            ObjectNode message = objectMapper.createObjectNode();
            message.put("role", "user");
            message.put("content", prompt);
            messages.add(message);

            requestBodyJson.set("messages", messages);

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBodyJson), headers);

            ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                System.out.println("Réponse brute : " + responseBody); // Débogage
                JSONObject jsonResponse = new JSONObject(responseBody);
                if (jsonResponse.has("choices") && jsonResponse.getJSONArray("choices").length() > 0) {
                    String generatedContent = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
                    System.out.println("Contenu généré : " + generatedContent);
                    scenarios.add(new TestScenario("GeneratedTest", generatedContent, "Pending"));
                } else {
                    System.out.println("Aucun contenu généré.");
                }
            } else {
                System.err.println("Erreur de l'API OpenAI : " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel à l'API OpenAI : " + e.getMessage());
            e.printStackTrace();
        }

        return scenarios;
    }
}