/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Marco
 */
public class StallResponseDto {
    
    private long id;
    private String name;
    private String photoFilePath;
    
    public StallResponseDto() {}

    public StallResponseDto(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }
    
}
