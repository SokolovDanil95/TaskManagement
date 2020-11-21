/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
public class EncrytedPasswordUtils {
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String args) {
        String encrytedPassword = encrytePassword(args);
 
        System.out.println("Encryted Password: " + encrytedPassword);
    }
 
}