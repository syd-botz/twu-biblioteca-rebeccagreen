package com.twu.biblioteca;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> bookList;

    public Library(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBookList() {
        bookList.add(new Book("Jane Eyre"));
        return bookList;
    }

}
