package com.twu.biblioteca;

import java.io.PrintStream;

public class Movie {
    private String title;
    private String year;
    private String director;
    private Integer rating;
    private Boolean isCheckedOut;

    
    public Movie(String title, String year, String director, Integer rating){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.isCheckedOut = false;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public boolean getIsCheckedOut() {
        return this.isCheckedOut;
    }

    public void checkOutMovie(){
        this.isCheckedOut = true;
    }

    public void printMovie(PrintStream printStream) {
        printStream.println(this.getTitle() + " " + this.getDirector() + " in " + this.getYear() + " with rating "
                + this.getRating());
    }
}
