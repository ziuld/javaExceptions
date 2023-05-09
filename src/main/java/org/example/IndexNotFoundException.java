package org.example;

public class IndexNotFoundException extends Exception{
    public IndexNotFoundException (){
        //empty
    }

    public IndexNotFoundException(String message){
        //our logic
        super(message);
    }

    public IndexNotFoundException(Throwable cause){
        //our logic
        super(cause);
    }

    public  IndexNotFoundException(String message, Throwable cause){
        //out logic
        super(message,cause);
    }
}
