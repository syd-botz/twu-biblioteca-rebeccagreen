package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;

// THIS IS MY TEST COMMENT

public class BibliotecaApp {

    private final BufferedReader reader;
    private Library library;
    private BibliotecaAppView bibliotecaAppView;
    private Boolean running;

    public BibliotecaApp(Library library, BufferedReader reader, BibliotecaAppView bibliotecaAppView) {
        this.library = library;
        this.reader = reader;
        this.bibliotecaAppView = bibliotecaAppView;
        this.running = true;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        bookList.add(new Book("1984", "George Orwell", "2010"));
        bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = new PrintStream(System.out);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);

        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, reader, bibliotecaAppView);

        app.start();
    }

    public void start() throws IOException {
        bibliotecaAppView.displayWelcomeMessage();
        String choice = getUserOptionChoice();
        while (running) {
            choice = handleUserChoice(choice);
        }
    }

    private String handleUserChoice(String choice) throws IOException {
        if (choice.equals("1")){
            library.printBooklist();
            return getUserOptionChoice();
        }
        else if (choice.equals("2")){
            instructUserToCheckOutBook();
            return getUserOptionChoice();
        }
        else if (choice.equals("3")){
            instructUserToReturnBook();
            return getUserOptionChoice();
        }
        else if(choice.equals("q")){
            bibliotecaAppView.showQuitMessage();
            running = false;
        }
        else{
            bibliotecaAppView.printInvalidInputMessage();
            return getUserOptionChoice();
        }
        return "";
    }

    private String getUserOptionChoice() throws IOException {
        bibliotecaAppView.displayOptionMenu();
        return reader.readLine().toLowerCase();
    }

    private String getUserTitleInput() throws IOException {
        return reader.readLine().toLowerCase();
    }

    private void instructUserToCheckOutBook() throws IOException{
        bibliotecaAppView.displayCheckOutBookInstructions();
        String bookToCheckOutTitle = getUserTitleInput();
        Boolean isBookSuccessfullyCheckedOut = library.checkOut(bookToCheckOutTitle);
        if (isBookSuccessfullyCheckedOut) {
            bibliotecaAppView.displayCheckOutBookSuccessful();
        }
        else {
            bibliotecaAppView.displayCheckOutBookNotSuccessful();
        }
    }

    private void instructUserToReturnBook() throws IOException {
        bibliotecaAppView.displayReturnBookInstructions();
        String bookToReturnTitle = getUserTitleInput();
        Boolean isBookSuccessfullyReturned = library.returnBook(bookToReturnTitle);
        if (isBookSuccessfullyReturned){
            bibliotecaAppView.displayReturnBookSuccessful();
        }else{
            bibliotecaAppView.displayReturnBookNotSuccessful();
        }
    }
}
