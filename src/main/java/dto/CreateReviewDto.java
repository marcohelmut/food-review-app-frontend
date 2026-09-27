/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Marco
 */
public class CreateReviewDto {
    
    int studentNumber;
    int priceScore;
    int tasteScore;
    int cleanlinessScore;
    String comment;
    long foodId;
    
    public CreateReviewDto() {}

    public CreateReviewDto(int studentNumber, int priceScore, int tasteScore, int cleanlinessScore, String comment, long foodId) {
        this.studentNumber = studentNumber;
        this.priceScore = priceScore;
        this.tasteScore = tasteScore;
        this.cleanlinessScore = cleanlinessScore;
        this.comment = comment;
        this.foodId = foodId;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getPriceScore() {
        return priceScore;
    }

    public int getTasteScore() {
        return tasteScore;
    }

    public int getCleanlinessScore() {
        return cleanlinessScore;
    }

    public String getComment() {
        return comment;
    }

    public long getFoodId() {
        return foodId;
    }
    
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    
    public void setPriceScore(int priceScore) {
        this.priceScore = priceScore;
    }

    public void setTasteScore(int tasteScore) {
        this.tasteScore = tasteScore;
    }

    public void setCleanlinessScore(int cleanlinessScore) {
        this.cleanlinessScore = cleanlinessScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }
    
}
