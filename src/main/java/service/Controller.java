/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Marco
 */
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class Controller {

    private static final String API_URL = "http://localhost:8080/api/stalls";
    private final HttpClient httpClient;

    public Controller() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    /**
     * Called by the Swing UI action listener (e.g., button click).
     *
     * @param stallName Raw input string from the Swing JTextField
     * @return String response message from the server or error
     */
    public String handleSaveStall(String stallName) {
        // 1. Basic client-side UI pre-check
        if (stallName == null || stallName.trim().isEmpty()) {
            return "Error: Stall name cannot be empty.";
        }

        try {
            // 2. Package string into JSON payload expected by Spring Boot
            // Example JSON: {"name": "Marco's Stall"}
            String jsonPayload = String.format("{\"name\": \"%s\"}", escapeJson(stallName.trim()));

            // 3. Build the HTTP POST request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            // 4. Send the request synchronously to Spring Boot backend
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // 5. Evaluate HTTP Response Status Code
            if (response.statusCode() == 201) {
                return "Success: Stall saved successfully!";
            } else if (response.statusCode() == 400) {
                return "Validation Error: " + response.body();
            } else {
                return "Server Error (Code " + response.statusCode() + "): " + response.body();
            }

        } catch (Exception e) {
            return "Connection Error: Failed to reach backend. " + e.getMessage();
        }
    }

    public String getStall(String stallName) {
        // 1. Basic client-side UI pre-check
        if (stallName == null || stallName.trim().isEmpty()) {
            return "Error: Stall name cannot be empty.";
        }

        try {
            // 2. Build the URI with URL encoding to safely handle spaces and special characters
            String encodedName = URLEncoder.encode(stallName.trim(), StandardCharsets.UTF_8);
            String fullUrl = API_URL + "?name=" + encodedName; // Adjust path if your backend uses path variables: API_URL + "/" + encodedName

            // 3. Build the HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // 4. Send the request synchronously to Spring Boot backend
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // 5. Evaluate HTTP Response Status Code
            if (response.statusCode() == 200) {
                return response.body(); // Returns the JSON payload of the stall
            } else if (response.statusCode() == 404) {
                return "Error: Stall not found.";
            } else if (response.statusCode() == 400) {
                return "Validation Error: " + response.body();
            } else {
                return "Server Error (Code " + response.statusCode() + "): " + response.body();
            }

        } catch (Exception e) {
            return "Connection Error: Failed to reach backend. " + e.getMessage();
        }
    }

    // Helper to escape quote characters inside text inputs
    private String escapeJson(String input) {
        return input.replace("\"", "\\\"");
    }
}
