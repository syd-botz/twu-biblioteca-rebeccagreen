package com.twu.biblioteca;

import com.sun.tools.internal.ws.wsdl.document.Output;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream mockPrintStream ;
    private Library mockLibrary;
    private BufferedReader bufferedReader;
    private BibliotecaAppView mockBibliotecaAppView;
    private BibliotecaApp app;

    @Before
    public void setUp() throws IOException {

      ArrayList<Book> bookList = new ArrayList<Book>();

      bookList.add(new Book("1984", "George Orwell", "2010"));
      bookList.add(new Book("Beloved", "Toni Morrison", "2005"));

//    reader = new BufferedReader(new InputStreamReader(System.in));
//      reader = mock(BufferedReader.class);
//      outputStream = new ByteArrayOutputStream();
//      out = new PrintStream(outputStream);
//      lib = new Library(out, bookList);
//      bibliotecaAppView = new BibliotecaAppView(out);

        bufferedReader = mock(BufferedReader.class);
        mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        mockLibrary = mock(Library.class);
        mockBibliotecaAppView = mock(BibliotecaAppView.class);
        app = new BibliotecaApp(mockLibrary, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);




//        when(reader.readLine()).thenReturn("1").thenReturn("q");
//        BibliotecaApp app = new BibliotecaApp(lib, outputStream, out, reader, bibliotecaAppView);
//       app.start();
    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();
        verify(mockLibrary).showWelcomeMessage();
    }

//    @Test
//    public void mock_shouldAppStartTriggerLibraryDisplayWelcomeMessage() throws IOException {
//        Library mockLibrary = mock(Library.class);
//        BibliotecaApp app = new BibliotecaApp(mockLibrary, outputStream, out, reader, bibliotecaAppView);
//        app.start();
//        verify(mockLibrary).showWelcomeMessage();
//    }

    @Test
    public void mock_shouldSeeIfStartIsCalled() throws IOException {
        BibliotecaApp mockApp = mock(BibliotecaApp.class);

        mockApp.start();

        verify(mockApp).start();
    }


    @Test
    public void shouldSee1984AfterWelcomeMessage() throws IOException {
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("1984"));
    }


    @Test
    public void shouldPrintTwoBooksAfterWelcomeMessage(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("1984"));
        assertThat(output[5], containsString("Beloved"));
    }

    @Test
    public void shouldPrintNumberOfBooksInBookList(){

        String[] output = outputStream.toString().split("\n");
        Integer numOfBooks = output.length-4;
        assertThat(numOfBooks, is(2));
    }

    @Test
    public void shouldPrintAuthorWhenListingBookTitleInBookList(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("George Orwell"));
    }

    @Test
    public void shouldPrintYearWhenListingBookTitleInBookList(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[4], containsString("2010"));
    }

    @Test
    public void shouldDisplayOptionsAfterWelcomeMessage(){
        String[] output = outputStream.toString().split("\n");
        assertThat(output[1], is("Options"));
        assertThat(output[2], is("1 - List of Books"));
        assertThat(output[3], is("Please Enter the Number of Your Choice Here: "));
    }

//    @Test
//    public void shouldSeeListOfBooksWhen1IsSelected() throws IOException {
////        user input gather from scanner is 1
////        Scanner scanner = new Scanner(System.in);
////        String userInput = scanner.nextLine();
//
//        BufferedReader bufferedReader = mock(BufferedReader.class);
//        when(bufferedReader.readLine()).thenReturn("1");
//
//        verify(bufferedReader)
//
////        set up scanner wrapper to always choose 1
//
////        then the library should display the book list
////        so we want to check to see if library calls displaybooklist
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

//        BibliotecaApp mockApp = new BibliotecaApp(mockLibrary, mockOutputStream, mockPrintStream, bufferedReader);

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