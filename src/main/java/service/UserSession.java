/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Marco
 */
public class UserSession {
    
    public static String token = "";
    
    public static String getJwt() {
        return token;
    }
    
    public static boolean isLoggedIn() {
        return !token.isBlank();
    }
    
}
