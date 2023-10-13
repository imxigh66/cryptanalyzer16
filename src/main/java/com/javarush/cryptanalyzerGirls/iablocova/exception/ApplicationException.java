package com.javarush.cryptanalyzerGirls.iablocova.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException (){

    }

    public ApplicationException (String message){
        super (message);
    }

    public ApplicationException (String message, Throwable cause){
        super (message, cause);
    }

    public ApplicationException (Throwable cause){
        super (cause);
    }
}