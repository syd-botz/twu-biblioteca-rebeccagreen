package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.*;

public class Library {

    private PrintStream printStream;
    private ArrayList<Book> bookList;
    private ArrayList<Movie> movieList;

    public Library(PrintStream printStream, ArrayList<Book> bookList, ArrayList<Movie> movieList) {

        this.printStream = printStream;
        this.bookList = bookList;
        this.movieList = movieList;
    }

    private Book isTitleInBookList(String bookTitle){
        for (Book book: bookList){
            if(book.getTitle().toLowerCase().equals(bookTitle)){
                return book;
            }
        }
        return null;
    }

    public void printBooklist(){
        for (Book book : bookList){
            if (!book.getIsCheckedOut()) {
                book.printBook(printStream);
            }
        }
    }

    public void printMovieList() {
        for (Movie movie: movieList){
            if (!movie.getIsCheckedOut()) {
                movie.printMovie(printStream);
            }
        }
    }

    public Boolean checkOutBook(String bookToCheckOutTitle) {
        Book bookToCheckOut = isTitleInBookList(bookToCheckOutTitle);
        if (bookToCheckOut != null){
            bookToCheckOut.checkOutBook();
            return true;
        }
        return false;
    }

    public Boolean returnBook(String bookToReturnTitle) {
        Book bookToReturn = isTitleInBookList(bookToReturnTitle);
        if (bookToReturn != null){
            bookToReturn.checkInBook();
            return true;
        }
        return false;
    }


    public Boolean checkOutMovie(String movieToCheckOutTitle) {
        Movie movieToCheckOut = isTitleInMovieList(movieToCheckOutTitle);
        if (movieToCheckOut != null){
            movieToCheckOut.checkOutMovie();
            return true;
        }
        return false;
    }

    private Movie isTitleInMovieList(String movieToCheckOutTitle) {
        for (Movie movie: movieList){
            if(movie.getTitle().toLowerCase().equals(movieToCheckOutTitle)){
                return movie;
            }
        }
        return null;
    }
}
