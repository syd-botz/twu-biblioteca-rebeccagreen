package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.*;

public class Library {

    private PrintStream printStream;
    private ArrayList<Book> bookList;

    public Library(PrintStream printStream, ArrayList<Book> bookList) {

        this.printStream = printStream;
        this.bookList = bookList;
    }

    private Book isTitleInBookList(String bookTitle){
        for (Book book: bookList){
            if(book.getTitle().toLowerCase().equals(bookTitle)){
                return book;
            }
        }
        return null;
    }

    public void printBooklist(){
        for (Book book : bookList){
            if (!book.getIsCheckedOut()) {
                book.printBook(printStream);
            }
        }
    }

    public Boolean checkOut(String bookToCheckOutTitle) {
        Book bookToCheckOut = isTitleInBookList(bookToCheckOutTitle);
        if (bookToCheckOut != null){
            bookToCheckOut.checkOutBook();
            return true;
        }
        return false;
    }

    public Boolean returnBook(String bookToReturnTitle) {
        Book bookToReturn = isTitleInBookList(bookToReturnTitle);
        if (bookToReturn != null){
            bookToReturn.checkInBook();
            return true;
        }
        return false;
    }
}
