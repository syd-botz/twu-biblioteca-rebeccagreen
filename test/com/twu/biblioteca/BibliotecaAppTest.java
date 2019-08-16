package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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
      String[] bookList = {"1984", "Beloved", "House of the Spirits", "How to Do Nothing", "Blah"};
      app.start(bookList);
    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() {
        assertThat(outputStream.toString(), containsString("welcome to rebecca and syd's library!"));
    }

    @Test
    public void shouldSee1984AfterWelcomeMessage() throws IOException {
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], is("1984"));
    }

    @Test
    public void shouldPrintTwoBooksAfterWelcomeMessage(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], is("1984"));
        assertThat(output[2], is("Beloved"));
    }

    @Test
    public void shouldPrintNumberOfBooksInBookList(){
        String[] output = outputStream.toString().split("\n");
        Integer numOfBooks = output.length-1;
        assertThat(5, is(numOfBooks));
    }

}