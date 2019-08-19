package com.twu.biblioteca;

import java.awt.print.Printable;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

public class Library {

    private PrintStream printStream;
    private ArrayList<Book> bookList;

    public Library(PrintStream printStream, ArrayList<Book> bookList) {

        this.printStream = printStream;
        this.bookList = bookList;
    }

//    public void showWelcomeMessage(){
//
//        printStream.println("welcome to rebecca and syd's library!");
//    }

    public void printBooklist(){
        for (Book book : bookList){
            book.printBook(printStream);
        }
    }


    public Boolean checkOut(String bookToCheckOutTitle) {
        for (Book book : bookList){
            if (book.getTitle().toLowerCase().equals(bookToCheckOutTitle)){
                book.checkOutBook();
                return true;
            }
        }
        return false;
    }
}
