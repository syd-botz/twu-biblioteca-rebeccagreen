package com.twu.biblioteca;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class BibliotecaApp {


    private OutputStream outputStream;

    public BibliotecaApp(OutputStream outputStream){
        this.outputStream = outputStream;
    }


//    private PrintStream ps = new PrintStream(outContent);

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(System.out);
        app.start();
//        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        Library library = new Library(new PrintStream(System.out));
//        library.showWelcomeMessage();
//        System.out.println("Welcome to Biblioteca! Your one-top shop for great book titles in Bangalore!");
    }

    public void start(){
        Library library = new Library(new PrintStream(outputStream));
        library.showWelcomeMessage();
    }
}
