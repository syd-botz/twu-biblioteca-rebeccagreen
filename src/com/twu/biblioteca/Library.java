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

//    public Boolean isTitleInBookList(String bookTitle){
//        for (Book book: bookList){
//            if(book.getTitle().toLowerCase().equals(bookTitle)){
//                return true;
//            }
//        }
//        return false;
//    }

    public void printBooklist(){
        for (Book book : bookList){
            if (!book.getIsCheckedOut()) {
                book.printBook(printStream);
            }
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

    public Boolean returnBook(String bookToReturnTitle) {
        for (Book book : bookList){
            if (book.getTitle().toLowerCase().equals(bookToReturnTitle)){
                book.checkInBook();
                return true;
            }
        }
        return false;
    }
}
