package com.twu.biblioteca;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaApp {

    private OutputStream outputStream;
    private Library library;
    private PrintStream printStream;


    public BibliotecaApp(Library library, OutputStream outputStream, PrintStream printStream) {
        this.outputStream = outputStream;
        this.library = library;
        this.printStream = printStream;
    }

    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        bookList.add(new Book("1984", "George Orwell", "2010"));
        bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(System.out);

        Library lib = new Library(printStream, bookList);

        BibliotecaApp app = new BibliotecaApp(lib, byteOutputStream, printStream);

        app.start();
    }

    public void start(){
//        need outputstream to create print stream to pass to library
//        PrintStream printStream = new PrintStream((outputStream));
        library.showWelcomeMessage();
        displayWelcomeMessage();
        library.printBooklist();
    }

    public void displayWelcomeMessage(){
        printStream.println("Options");
        printStream.println("1 - List of Books");
        printStream.println("Please Enter the Number of Your Choice Here: ");
    }
}
