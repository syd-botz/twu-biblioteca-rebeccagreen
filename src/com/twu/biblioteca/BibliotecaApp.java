package com.twu.biblioteca;

import sun.font.TrueTypeFont;

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
        bibliotecaAppView.displayWelcomeMessage();
        bibliotecaAppView.displayOptionMenu();
        Boolean running = true;
        String choice = getUserInput();

        // This should be refactored into smaller methods
        while (running){
            if (choice.equals("1")){
                library.printBooklist();
                bibliotecaAppView.displayOptionMenu();
                choice = getUserInput().toLowerCase();
            }
            if (choice.equals("2")){
                bibliotecaAppView.displayCheckOutBookInstructions();
                String bookToCheckOutTitle = getUserInput().toLowerCase();
                Boolean isBookSuccessfullyCheckedOut = library.checkOut(bookToCheckOutTitle);
                if (isBookSuccessfullyCheckedOut) {
                    bibliotecaAppView.displayCheckOutBookSuccessful();
                }
                else {
                    bibliotecaAppView.displayCheckOutBookNotSuccessful();
                }
                bibliotecaAppView.displayOptionMenu();
                choice = getUserInput().toLowerCase();
            }
            if (choice.equals("3")){
                bibliotecaAppView.displayReturnBookInstructions();
                String bookToReturnTitle = getUserInput().toLowerCase();
                Boolean isBookSuccessfullyReturned = library.returnBook(bookToReturnTitle);
                if (isBookSuccessfullyReturned){
                    bibliotecaAppView.displayReturnBookSuccessful();
                }else{
                    bibliotecaAppView.displayReturnBookNotSuccessful();
                }
                bibliotecaAppView.displayOptionMenu();
                choice = getUserInput().toLowerCase();
            }
            else if(choice.equals("q")){
                bibliotecaAppView.showQuitMessage();
                running = false;
            }
            else{
                bibliotecaAppView.printInvalidInputMessage();
                bibliotecaAppView.displayOptionMenu();
                choice = getUserInput().toLowerCase();

            }

        }

    }

    private String getUserInput() throws IOException {
        return reader.readLine();
    }

}
