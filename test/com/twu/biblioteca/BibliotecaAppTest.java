package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream mockPrintStream ;
    private Library mockLibrary;
    private BufferedReader bufferedReader;
    private BibliotecaAppView mockBibliotecaAppView;
    private BibliotecaApp app;

    @Before
    public void setUp() {

        bufferedReader = mock(BufferedReader.class);
        mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        mockLibrary = mock(Library.class);
        mockBibliotecaAppView = mock(BibliotecaAppView.class);
        app = new BibliotecaApp(mockLibrary, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();
        verify(mockBibliotecaAppView).displayWelcomeMessage();
    }

    @Test
    public void shouldDisplayOptionsAfterWelcomeMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();
        verify(mockBibliotecaAppView).displayOptionMenu();
    }


    @Test
    public void mock_shouldSeeIfStartIsCalled() throws IOException {
        BibliotecaApp mockApp = mock(BibliotecaApp.class);
        mockApp.start();
        verify(mockApp).start();
    }


    @Test
    public void shouldPrintOneBookWhenOption1IsSelectedAndThereIsOneBookInTheLibrary() throws IOException {
        // make app with library with one mock book in it

        ArrayList<Book> bookList = new ArrayList<Book>();
        Book mockBook = mock(Book.class);
        bookList.add(mockBook);
        Library libWithMockBook = new Library(mockPrintStream, bookList);
        OutputStream mockOutputStream = mock(OutputStream.class);
        app = new BibliotecaApp(libWithMockBook, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

        // Choose option 1
        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();

        // check if print book is called on mock book one time
        verify(mockBook).printBook(mockPrintStream);
    }


    @Test
    public void shouldPrintTwoBooksWhenOption1IsSelectedAndThereAreTwoBooksInTheLibrary() throws IOException {
        // make app with library with one mock book in it

        ArrayList<Book> bookList = new ArrayList<Book>();
        Book mockBook = mock(Book.class);
        Book mockBook2 = mock(Book.class);
        bookList.add(mockBook);
        bookList.add(mockBook2);
        Library libWithMockBook = new Library(mockPrintStream, bookList);
        OutputStream mockOutputStream = mock(OutputStream.class);
        app = new BibliotecaApp(libWithMockBook, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

        // Choose option 1
        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();

        // check if print book is called on mock book one time
        verify(mockBook, times(1)).printBook(mockPrintStream);
        verify(mockBook2, times(1)).printBook(mockPrintStream);
    }

//    @Test
//    public void shouldPrintAuthorWhenListingBookTitleInBookList(){
////         create a book view that formats how to print a book
////        test if printAuthor & printTitle is called on the book
//
//        String[] output = outputStream.toString().split("\n");
//        assertThat(output[4], containsString("George Orwell"));
//    }
//
//    @Test
//    public void shouldPrintYearWhenListingBookTitleInBookList(){
////        see above
//        String[] output = outputStream.toString().split("\n");
//        assertThat(output[4], containsString("2010"));
//    }


    @Test
    public void shouldDisplayWhen1IsSelectedFromOptions() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        PrintStream mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Library mockLibrary = mock(Library.class);
        BibliotecaAppView mockBibliotecaAppView = mock(BibliotecaAppView.class);
        BibliotecaApp app = new BibliotecaApp(mockLibrary, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();
        verify(mockLibrary).printBooklist();
    }

    @Test
    public void shouldInformUserOfInValidInputWhenInputIsNot1() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        PrintStream mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Library mockLibrary = mock(Library.class);
        BibliotecaAppView mockBibliotecaAppView = mock(BibliotecaAppView.class);
        BibliotecaApp app = new BibliotecaApp(mockLibrary, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("2").thenReturn("1").thenReturn("q");

        app.start();
        verify(mockBibliotecaAppView, atLeastOnce()).printInvalidInputMessage();
    }
    @Test
    public void shouldQuitApplicationWhenUserInputsQFromOptionMenu() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        PrintStream mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Library mockLibrary = mock(Library.class);
        BibliotecaAppView mockBibliotecaAppView = mock(BibliotecaAppView.class);
        BibliotecaApp app = new BibliotecaApp(mockLibrary, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).showQuitMessage();
    }
}