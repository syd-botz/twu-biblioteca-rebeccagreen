package com.twu.biblioteca;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaApp {

    private OutputStream outputStream;

    public BibliotecaApp(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(System.out);

        ArrayList<Map<String, String>> bookList = new ArrayList();
        Map<String, String> book1 = new HashMap<String, String>();
        Map<String, String> book2 = new HashMap<String, String>();


        book1.put("title", "1984");
        book1.put("author", "George Orwell");
        book1.put("year", "2010");
        book2.put("title", "Beloved");
        book2.put("author", "Toni Morrison");
        book2.put("year", "2005");

        bookList.add(book1);
        bookList.add(book2);

        app.start(bookList);
    }

    public void start(ArrayList<Map<String, String>> bookList){
        Library library = new Library(new PrintStream(outputStream), bookList);
        library.showWelcomeMessage();
        library.printBook();
    }
}
