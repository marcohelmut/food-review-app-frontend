/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Marco
 */
public class CreateStallDto {
    
    private String name;
    private String photoFilePath;
    
    public CreateStallDto() {}

    public CreateStallDto(String name, String photoFilePath) {
        this.name = name;
        this.photoFilePath = photoFilePath;
    }

    public String getName() {
        return name;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }
    
}
