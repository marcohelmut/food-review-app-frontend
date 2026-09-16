/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

public class CreateFoodDto {
    
    String name;
    double price;
    long stallId;
    String foodPhotoFilePath;
    
    public CreateFoodDto() {}

    public CreateFoodDto(String name, double price, long stallId, String foodPhotoFilePath) {
        this.name = name;
        this.price = price;
        this.stallId = stallId;
        this.foodPhotoFilePath = foodPhotoFilePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getStallId() {
        return stallId;
    }

    public String getFoodPhotoFilePath() {
        return foodPhotoFilePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStallId(long stallId) {
        this.stallId = stallId;
    }

    public void setFoodPhotoFilePath(String foodPhotoFilePath) {
        this.foodPhotoFilePath = foodPhotoFilePath;
    }
    
}
