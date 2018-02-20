/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.export;
 
/**
 *
 * @author DEV
 */
public class ExportationException extends RuntimeException{
 
    public ExportationException() {
        super();
    }
 
    public ExportationException(String message) {
        super(message);
    }
 
    public ExportationException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public ExportationException(Throwable cause) {
        super(cause);
    }
 
    public ExportationException(String message, Throwable cause, boolean bln, boolean bln1) {
        //super(message, cause, bln, bln1);
    }
    
}