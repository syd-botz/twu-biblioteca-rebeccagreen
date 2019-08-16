package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private Library lib;

    @Before
    public void setUp() {
//      outputStream = new ByteArrayOutputStream();
//      out = new PrintStream(outputStream);
//      BibliotecaApp app = new BibliotecaApp(outputStream);
//
//      ArrayList<Book> bookList = new ArrayList<Book>();
//
//      bookList.add(new Book("1984", "George Orwell", "2010"));
//      bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

      ArrayList<Book> bookList = new ArrayList<Book>();

      bookList.add(new Book("1984", "George Orwell", "2010"));
      bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

      outputStream = new ByteArrayOutputStream();
      out = new PrintStream(outputStream);
      lib = new Library(out, bookList);

      BibliotecaApp app = new BibliotecaApp(lib, outputStream, out);
      app.start();
    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() {
        assertThat(outputStream.toString(), containsString("welcome to rebecca and syd's library!"));
    }

//    @Test
//    public void mock_shouldAppStartTriggerLibraryDisplayWelcomeMessage(){
//
//    }

    @Test
    public void mock_shouldSeeIfStartIsCalled() {
        BibliotecaApp mockApp = mock(BibliotecaApp.class);

        mockApp.start();

        verify(mockApp).start();
    }


    @Test
    public void shouldSee1984AfterWelcomeMessage() throws IOException {
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("1984"));
    }

//    @Test
//    public void shouldCallPrintBooklistAfterWelcomeMessageDisplays(){
//        Library mockLibrary = mock(Library.class);
//        verify(mockLibrary, atLeast(1)).printBooklist();
//    }

    @Test
    public void shouldPrintTwoBooksAfterWelcomeMessage(){

//        String[] output = outputStream.toString().split("\n");
//        assertThat(output[1], containsString("1984"));
//        assertThat(output[2], containsString("Beloved"));
    }

    @Test
    public void shouldPrintNumberOfBooksInBookList(){
        String[] output = outputStream.toString().split("\n");
        Integer numOfBooks = output.length-4;
        assertThat(numOfBooks, is(2));
    }

    @Test
    public void shouldPrintAuthorWhenListingBookTitleInBookList(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("George Orwell"));
    }

    @Test
    public void shouldPrintYearWhenListingBookTitleInBookList(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("2010"));
    }

    @Test
    public void shouldDisplayOptionsAfterWelcomeMessage(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], is("Options"));
        assertThat(output[2], is("1 - List of Books"));
        assertThat(output[3], is("Please Enter the Number of Your Choice Here: "));
    }

//    @Test
//    public void shouldSeeListOfBooksWhen1IsSelected() throws IOException {
////        user input gather from scanner is 1
////        Scanner scanner = new Scanner(System.in);
////        String userInput = scanner.nextLine();
//
//        BufferedReader bufferedReader = mock(BufferedReader.class);
//        when(bufferedReader.readLine()).thenReturn("1");
//
//        verify(bufferedReader)
//
////        set up scanner wrapper to always choose 1
//
////        then the library should display the book list
////        so we want to check to see if library calls displaybooklist
//    }
}