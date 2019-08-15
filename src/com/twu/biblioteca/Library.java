package com.twu.biblioteca;

import java.awt.print.Printable;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Library {

    private PrintStream printStream;

    public Library(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void showWelcomeMessage(){
        printStream.print("welcome");
    }


}
