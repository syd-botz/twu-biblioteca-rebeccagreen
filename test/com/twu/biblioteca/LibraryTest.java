package com.twu.biblioteca;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LibraryTest {
    @Test
    public void shouldReturnLibraryWithOneBookTitledJaneEyreWhenGetBookListReturnsLibraryWithOneBookTitledJaneEyre() {
//      given new library

        Book janeEyre = new Book("Jane Eyre");
        ArrayList<Book> testBookList = new ArrayList<Book>();
        testBookList.add(janeEyre);

        Library lib = new Library(testBookList);

//      when
        ArrayList<Book> actualBookList = lib.getBookList();

//      then
        assertThat(actualBookList, is(testBookList));
    }
}