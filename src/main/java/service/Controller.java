/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Marco
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CreateFoodDto;
import dto.CreateReviewDto;
import dto.CreateStallDto;
import dto.FoodResponseDto;
import dto.ReviewResponseDto;
import dto.StallResponseDto;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Controller {

    private final ObjectMapper mapper;
    private final String BASE_URL = "https://localhost:8443/api";
    private final HttpClient client;
    public String jwtToken = "";

    public Controller() {
        this.client = createHttpsClient();
        this.mapper = new ObjectMapper();
    }
    
    private HttpClient createHttpsClient() {
        try {
            KeyStore store = KeyStore.getInstance("PKCS12");
            
            try (InputStream is = getClass().getResourceAsStream("/keystore.p12")) {
                if (is == null) {
                    throw new IllegalStateException("Keystore not found");
                }
                
                String password = System.getenv("SSL_KEYSTORE_PASSWORD");
                store.load(is, password.toCharArray());
                
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                        TrustManagerFactory.getDefaultAlgorithm()
                );
                tmf.init(store);
                
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, tmf.getTrustManagers(), null);
                
                //System.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
                
                return HttpClient.newBuilder()
                        .sslContext(sslContext)
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize HTTPS client");
        }
    }

    public StallResponseDto saveStall(CreateStallDto stall) throws JsonProcessingException, IOException, InterruptedException {
        String stallJson = mapper.writeValueAsString(stall);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + UserSession.token)
                .POST(HttpRequest.BodyPublishers.ofString(stallJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), StallResponseDto.class);
    }

    public StallResponseDto getStallByName(String stallName) throws IOException, InterruptedException {
        String encodedStallName = URLEncoder.encode(stallName, StandardCharsets.UTF_8).replace("+", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls/name/" + encodedStallName))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), StallResponseDto.class);
    }
    
    public StallResponseDto getStallById(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls/" + id))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), StallResponseDto.class);
    }

    public List<StallResponseDto> getStalls() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), new TypeReference<List<StallResponseDto>>() {
        });
    }

    public List<FoodResponseDto> getFoodsByStall(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods/stall/" + id))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), new TypeReference<List<FoodResponseDto>>() {
        });
    }

    public List<ReviewResponseDto> getReviewsByFood(long foodId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/reviews/foods/" + foodId))
                .header("Accept", "application/json")
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), new TypeReference<List<ReviewResponseDto>>() {
        });
    }

    public FoodResponseDto saveFood(CreateFoodDto food) throws JsonProcessingException, IOException, InterruptedException {
        String foodJson = mapper.writeValueAsString(food);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + UserSession.token)
                .POST(HttpRequest.BodyPublishers.ofString(foodJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), FoodResponseDto.class);
    }

    public ReviewResponseDto saveReview(CreateReviewDto review) throws JsonProcessingException, IOException, InterruptedException {
        String reviewJson = mapper.writeValueAsString(review);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/reviews"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + UserSession.token)
                .POST(HttpRequest.BodyPublishers.ofString(reviewJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), ReviewResponseDto.class);
    }

    public void deleteStall(long stallId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls/" + stallId))
                .header("Authorization", "Bearer " + UserSession.token)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
    }

    public void deleteFood(long foodId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods/" + foodId))
                .header("Authorization", "Bearer " + UserSession.token)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
    }
    
    public long getTotalFoodCount() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods/count"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), long.class);
    }
    
    public long getTotalStallCount() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls/count"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), long.class);
    }
    
    public long getTotalReviewCount() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/reviews/count"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), long.class);
    }
    
    public List<FoodResponseDto> getPriceRanking() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods/rankings/price"))
                .header("Accept", "application/json")
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), new TypeReference<List<FoodResponseDto>>() {
        });
    }
    
    public List<FoodResponseDto> getTasteRanking() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods/rankings/taste"))
                .header("Accept", "application/json")
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), new TypeReference<List<FoodResponseDto>>() {
        });
    }
    
    public List<FoodResponseDto> getCleanlinessRanking() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods/rankings/cleanliness"))
                .header("Accept", "application/json")
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }
        
        return mapper.readValue(response.body(), new TypeReference<List<FoodResponseDto>>() {
        });
    }
    
    public String signup(String username, char[] password) throws IOException, InterruptedException {
        String responseText;
        
        try {
            // Build the request body payload
            // Note: If you don't have a custom DTO class for User payload, Map<String, String> works seamlessly with Jackson
            Map<String, String> payload = new HashMap<>();
            payload.put("username", username);
            payload.put("password", new String(password));

            String jsonPayload = mapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/v1/auth/signup")) // Update to BASE_URL + "/auth/signup" if mapped under /auth
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
            }

            responseText = response.body();
        } finally {
            // Zero out password array from memory immediately for security
            if (password != null) {
                Arrays.fill(password, '\0');
            }
        }

        return responseText;
    }
    
    public String login(String username, char[] password) throws IOException, InterruptedException {
        String token;

        try {
            // Build the JSON payload matching the backend's User class/body
            Map<String, String> payload = new HashMap<>();
            payload.put("username", username);
            payload.put("password", new String(password));

            String jsonPayload = mapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/v1/auth/login")) // Adjust to BASE_URL + "/auth/login" if nested
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
            }

            token = response.body();
        } finally {
            // Immediately overwrite password array in memory
            if (password != null) {
                Arrays.fill(password, '\0');
            }
        }
        
        if (token.startsWith("\"") && token.endsWith("\"") && token.length() > 1) {
                token = token.substring(1, token.length() - 1);
            }

        UserSession.token = token;
        return token;
    }

}
