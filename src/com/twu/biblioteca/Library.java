package com.twu.biblioteca;

import java.awt.print.Printable;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Library {

    private PrintStream printStream;
    private String[] bookList;

    public Library(PrintStream printStream, String[] bookList) {

        this.printStream = printStream;
        this.bookList = bookList;
    }

    public void showWelcomeMessage(){

        printStream.println("welcome to rebecca and syd's library!");
    }

    public void printBook(){
        for (String book : bookList){
            printStream.println(book);
        }
//        printStream.println("1984");
//        printStream.println("Beloved");
    }


}
