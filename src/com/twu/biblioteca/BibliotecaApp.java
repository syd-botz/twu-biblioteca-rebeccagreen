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
    private BibliotecaAppView bibliotecaAppView;



    public BibliotecaApp(Library library, OutputStream outputStream, PrintStream printStream, BufferedReader reader, BibliotecaAppView bibliotecaAppView) {
        this.outputStream = outputStream;
        this.library = library;
        this.printStream = printStream;
        this.reader = reader;
        this.bibliotecaAppView = bibliotecaAppView;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        bookList.add(new Book("1984", "George Orwell", "2010"));
        bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(System.out);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);

        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, byteOutputStream, printStream, reader, bibliotecaAppView);



        app.start();
    }

    public void start() throws IOException {
//        need outputstream to create print stream to pass to library
//        PrintStream printStream = new PrintStream((outputStream));
        library.showWelcomeMessage();
        bibliotecaAppView.displayWelcomeMessage();
        String choice = getUserInput();

        while(!choice.equals("1")){
            bibliotecaAppView.printInvalidInputMessage();
            bibliotecaAppView.displayWelcomeMessage();
            choice = getUserInput();
        }
        library.printBooklist();
    }

//    public void displayWelcomeMessage(){
//        printStream.println("Options");
//        printStream.println("1 - List of Books");
//        printStream.println("Please Enter the Number of Your Choice Here: ");
//    }

    private String getUserInput() throws IOException {
        return reader.readLine();
    }

//    public void printInvalidInputMessage() {
//        printStream.println("Please Enter a Valid Option");
//    }
}
