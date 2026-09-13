/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Marco
 */
public class FoodDto {
    
    private String name;
    private double price;
    private int stallId;

    public FoodDto(String name, double price, int stallId) {
        this.name = name;
        this.price = price;
        this.stallId = stallId;
    }
    
}
