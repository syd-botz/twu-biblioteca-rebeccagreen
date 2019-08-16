package com.twu.biblioteca;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class BibliotecaApp {

    private OutputStream outputStream;

    public BibliotecaApp(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(System.out);
        String[] bookList = {"1984", "Beloved", "House of the Spirits", "How to Do Nothing", "Blah"};
        app.start(bookList);
    }

    public void start(String[] bookList){
        Library library = new Library(new PrintStream(outputStream), bookList);
        library.showWelcomeMessage();
        library.printBook();
    }
}
