/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.swing.JTextField;

/**
 *
 * @author Marco
 */
public class Service {

    public int validateIntegerData(JTextField field, String fieldName) throws IllegalArgumentException {
        String input = field.getText().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }

        try {
            int data = Integer.parseInt(input);

            if (data <= 0) {
                throw new IllegalArgumentException(fieldName + " must be valid.");
            }

            if (fieldName.equals("Student Number")) {
                int length = input.length();
                if (length != 6) {
                    throw new IllegalArgumentException(fieldName + " must have six digits");
                }
                return data;
            }

            if (data > 5) {
                throw new IllegalArgumentException(fieldName + " must be between 1 & 5.");
            }
            
            return data;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

}
