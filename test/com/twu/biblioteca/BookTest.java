package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void bookConstructorTest() {
//        given
        Book janeEyre = new Book("Jane Eyre");
//        when
        String title = janeEyre.title();
//        then
        assertThat("Jane Eyre", title);
    }
//    given

//    when
//    then

}