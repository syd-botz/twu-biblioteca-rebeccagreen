package com.twu.biblioteca;

import java.awt.print.Printable;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

public class Library {

    private PrintStream printStream;
    private ArrayList<Map<String, String>> bookList;

    public Library(PrintStream printStream, ArrayList<Map<String, String>> bookList) {

        this.printStream = printStream;
        this.bookList = bookList;
    }

    public void showWelcomeMessage(){

        printStream.println("welcome to rebecca and syd's library!");
    }

    public void printBook(){
        for (Map<String, String> book : bookList){
            printStream.println(book.get("title") + " " + book.get("author") + " in " + book.get("year"));
        }
    }


}
