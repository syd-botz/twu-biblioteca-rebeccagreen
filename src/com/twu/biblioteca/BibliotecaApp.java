package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaApp {

    private final BufferedReader reader;
    private OutputStream outputStream;
    private Library library;
    private PrintStream printStream;


    public BibliotecaApp(Library library, OutputStream outputStream, PrintStream printStream, BufferedReader reader) {
        this.outputStream = outputStream;
        this.library = library;
        this.printStream = printStream;
        this.reader = reader;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        bookList.add(new Book("1984", "George Orwell", "2010"));
        bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(System.out);

        Library lib = new Library(printStream, bookList);

        BibliotecaApp app = new BibliotecaApp(lib, byteOutputStream, printStream, reader);

        app.start();
    }

    public void start() throws IOException {
//        need outputstream to create print stream to pass to library
//        PrintStream printStream = new PrintStream((outputStream));
        library.showWelcomeMessage();
        displayWelcomeMessage();
        String choice = getUserInput();
        if (choice.equals("1")) {
            library.printBooklist();
        }
    }

    public void displayWelcomeMessage(){
        printStream.println("Options");
        printStream.println("1 - List of Books");
        printStream.println("Please Enter the Number of Your Choice Here: ");
    }

    private String getUserInput() throws IOException {
        return reader.readLine();
    }
}
