package com.twu.biblioteca;

import java.io.PrintStream;

public class Book {
    private String title;
    private String author;
    private String year;
    private Boolean isCheckedOut;

    public Book(String title, String author, String year) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.isCheckedOut = false;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return this.year;
    }

    public Boolean getIsCheckedOut() {
        return isCheckedOut;
    }

    public void checkOutBook(){
        this.isCheckedOut = true;
    }

// To Do : Make into Book View Class
    public void printBook(PrintStream printStream){
        printStream.println(this.getTitle() + " " + this.getAuthor() + " in " + this.getYear());

    }
}
