package com.twu.biblioteca;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LibraryTest {

//    set up method

    @Test
    public void shouldReturnBookJaneEyreFromLibraryWhenLibraryOnlyHasOneBook() {
        Library lib = new Library();
//      when
        Book actualBook = lib.getBook();
//      then
        assertThat(actualBook.getTitle(), is("Jane Eyre"));
    }

    @Test
    public void shouldPrintBookTitleWhenIStartTheApp() {

    }

//    @Test
//    public void shouldReturnLibraryWithOneBookTitledJaneEyreWhenGetBookListReturnsLibraryWithOneBookTitledJaneEyre() {
////      given new library
//
//        Book janeEyre = new Book("Jane Eyre");
//        ArrayList<Book> testBookList = new ArrayList<Book>();
//        testBookList.add(janeEyre);
////        we needed to do that because we needed to do it to create a library
//
//        Library lib = new Library(testBookList);
//
////      when
//        ArrayList<Book> actualBookList = lib.getBookList();
//
////      then
//        assertThat(actualBookList, is(testBookList));
//    }
//
//    @Test
//    public void shouldReturnBookListWith1984WhenConstructedWithBookListContaining1984() {
//        Book nineteenEightyFour = new Book("1984");
//        ArrayList<Book> testBookList = new ArrayList<Book>();
//        testBookList.add(nineteenEightyFour);
//        Library lib = new Library(testBookList);
//
//        ArrayList<Book> actualBookList = lib.getBookList();
//        System.out.println(actualBookList);
//
//        assertThat(actualBookList, is(testBookList));
//    }
}