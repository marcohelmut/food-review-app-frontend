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
import dto.CreateStallDto;
import dto.FoodResponseDto;
import dto.StallResponseDto;
import java.util.List;
import java.io.IOException;

public class Controller {

    private final ObjectMapper mapper;
    private final String BASE_URL = "http://localhost:8080/api";
    private final HttpClient client;

    public Controller() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public StallResponseDto saveStall(CreateStallDto stall) throws JsonProcessingException, IOException, InterruptedException {
        String stallJson = mapper.writeValueAsString(stall);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/stalls"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
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
                .uri(URI.create(BASE_URL + "/stalls/" + encodedStallName))
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
        
        return mapper.readValue(response.body(), new TypeReference<List<StallResponseDto>>() {});
    }
    
    public FoodResponseDto saveFood(CreateFoodDto food) throws JsonProcessingException, IOException, InterruptedException {
        String foodJson = mapper.writeValueAsString(food);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/foods"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(foodJson))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP Error " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), FoodResponseDto.class);
    }

}
