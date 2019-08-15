package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void bookConstructorTest() {
//        given
        Book janeEyre = new Book("Jane Eyre");
//        when
        String title = janeEyre.getTitle();
//        then
        assertThat(title, is("Jane Eyre"));
    }
//    given

//    when
//    then

}