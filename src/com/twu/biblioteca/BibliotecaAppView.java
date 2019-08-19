package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaAppView {
    private final PrintStream printStream;

    public BibliotecaAppView(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printInvalidInputMessage() {
        printStream.println("Please Enter a Valid Option");
    }

    public void displayWelcomeMessage(){
        printStream.println("welcome to rebecca and syd's library! \n");
    }

    public void displayOptionMenu() {
        printStream.println("Options");
        printStream.println("1 - List of Books");
        printStream.println("2 - Checkout Book");
        printStream.println("q - leave the library");
        printStream.println("Please Enter the Number of Your Choice Here: ");
    }

    public void showQuitMessage() {
        printStream.println("You are quitting the application.");
    }

    public void displayCheckOutBookInstructions() {
        printStream.println("Please Enter The Title of the Book To Checkout: ");
    }

    public void displayCheckOutBookSuccessful(){
        printStream.println("Thank you! Enjoy the Book!");
    }
}
