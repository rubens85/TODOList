/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.handleexception;

/**
 *
 * @author rubens
 */
public class UserNotFoundException extends Exception {
    
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
