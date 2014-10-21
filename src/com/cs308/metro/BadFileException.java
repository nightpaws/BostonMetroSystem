package com.cs308.metro;

public class BadFileException extends Exception {
    private static final long serialVersionUID = 5;

    public BadFileException(String theFileName) {
        System.out.println("Error: text input file not found.");
        System.out.println("Place " +  theFileName + " into the workspace directory and try again.");
        System.exit(1);
    }
}
