package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private Library lib;

    @Before
    public void setUp() {
      outputStream = new ByteArrayOutputStream();
      out = new PrintStream(outputStream);
      BibliotecaApp app = new BibliotecaApp(outputStream);

      ArrayList<Map<String, String>> bookList = new ArrayList();
      Map<String, String> book1 = new HashMap<String, String>();
      Map<String, String> book2 = new HashMap<String, String>();


      book1.put("title", "1984");
      book1.put("author", "George Orwell");
      book2.put("title", "Beloved");
      book2.put("author", "Toni Morrison");

      bookList.add(book1);
      bookList.add(book2);

      app.start(bookList);
    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() {
        assertThat(outputStream.toString(), containsString("welcome to rebecca and syd's library!"));
    }

    @Test
    public void shouldSee1984AfterWelcomeMessage() throws IOException {
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], containsString("1984"));
    }

    @Test
    public void shouldPrintTwoBooksAfterWelcomeMessage(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], containsString("1984"));
        assertThat(output[2], containsString("Beloved"));
    }

    @Test
    public void shouldPrintNumberOfBooksInBookList(){
        String[] output = outputStream.toString().split("\n");
        Integer numOfBooks = output.length-1;
        assertThat(numOfBooks, is(2));
    }

    @Test
    public void shouldPrintAuthorWhenListingBookTitleInBookList(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], containsString("George Orwell"));
    }

}