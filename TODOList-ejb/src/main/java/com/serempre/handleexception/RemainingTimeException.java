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
public class RemainingTimeException extends Exception {
    
    public RemainingTimeException(String errorMessage) {
        super(errorMessage);
    }
}
