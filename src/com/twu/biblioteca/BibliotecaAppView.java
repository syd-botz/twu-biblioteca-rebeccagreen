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
        printStream.println("Options");
        printStream.println("1 - List of Books");
        printStream.println("Please Enter the Number of Your Choice Here: ");
    }

    public void showQuitMessage() {
        printStream.println("You are quitting the application.");
    }
}
