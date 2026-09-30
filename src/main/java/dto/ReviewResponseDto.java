/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.Instant;

/**
 *
 * @author Marco
 */
public class ReviewResponseDto {
    
    long id;
    int studentNumber;
    String studentNickname;
    int priceScore;
    int tasteScore;
    int cleanlinessScore;
    String comment;
    long foodId;
    String createdAt;
    
    public ReviewResponseDto() {}

    public ReviewResponseDto(long id, int studentNumber, String studentNickname, int priceScore, int tasteScore, int cleanlinessScore, String comment, long foodId, String createdAt) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.studentNickname = studentNickname;
        this.priceScore = priceScore;
        this.tasteScore = tasteScore;
        this.cleanlinessScore = cleanlinessScore;
        this.comment = comment;
        this.foodId = foodId;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getStudentNickname() {
        return studentNickname;
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

    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setStudentNickname(String studentNickname) {
        this.studentNickname = studentNickname;
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

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
}
