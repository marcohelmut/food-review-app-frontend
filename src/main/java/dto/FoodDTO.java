/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Marco
 */
public class FoodDTO {
    
    private String name;
    private double price;
    private int stallId;

    public FoodDTO(String name, double price, int stallId) {
        this.name = name;
        this.price = price;
        this.stallId = stallId;
    }
    
}
