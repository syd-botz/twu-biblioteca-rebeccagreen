package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
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
      //lib = new Library(out);
    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() {
        BibliotecaApp app = new BibliotecaApp(outputStream);
        app.start();
        assertThat(outputStream.toString(), containsString("welcome"));
    }

}