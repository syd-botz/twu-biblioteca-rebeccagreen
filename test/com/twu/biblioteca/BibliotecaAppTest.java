package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static sun.nio.cs.Surrogate.is;

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

    @Test
    // Checkout is an option in the menu of options
    public void shouldDisplayUserOptionToCheckOutBookInMenu() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, byteOutputStream, printStream, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();
        assertThat(output, containsString("2 - Checkout Book"));
    }

    @Test
    public void shouldPromptUserToEnterABookTitleWhenOption2FromMenuIsSelected() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, byteOutputStream, printStream, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("2").thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();
        assertThat(output, containsString("Please Enter The Title of the Book To Checkout: "));
    }

    // QUESTION: should everything that can possibly be a mock be made one?
    // Protocol on writing a test that cannot compile?
    // Should this actually test if the code correctly notifies the user?
    @Test
    public void shouldSetCheckoutToTrueWhenUserTypesBelovedWhenPromptedToCheckOutBook() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book beloved = new Book("Beloved", "Toni Morrison", "2010");
        bookList.add(beloved);

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, byteOutputStream, printStream, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("2").thenReturn("Beloved").thenReturn("q");

        app.start();

       assertTrue(beloved.getIsCheckedOut());
    }

    @Test
    public void shouldDisplayErrorMessageIfUserInputsTitleToCheckOutThatIsNotAvailable(){

    }

    // EDIT AFTER ABOVE TEST RUNS
    @Test
    public void shouldNotCallPrintBookInPrintBookListMethodOnBookWhenBookIsCheckedOut() throws IOException {
        // make app with library with one mock book in it

        ArrayList<Book> bookList = new ArrayList<Book>();
        Book mockCheckedOutBook = mock(Book.class);
        Book mockCheckedInBook = mock(Book.class);
        bookList.add(mockCheckedOutBook);
        bookList.add(mockCheckedInBook);
        Library libWithMockBook = new Library(mockPrintStream, bookList);
        OutputStream mockOutputStream = mock(OutputStream.class);
        app = new BibliotecaApp(libWithMockBook, mockOutputStream, mockPrintStream, bufferedReader, mockBibliotecaAppView);

        // check in checkedin book
        when(mockCheckedInBook.getIsCheckedOut()).thenReturn(false);
        // check out checkedout book
        when(mockCheckedInBook.getIsCheckedOut()).thenReturn(true);

        // NEED TO EDIT THIS LINE SO THAT USER INPUT IS THE MOCK BOOK
        // Choose option 2 - CheckoutBooks
        when(bufferedReader.readLine()).thenReturn("2").thenReturn("q");

        app.start();

        verify(mockCheckedInBook, times(0)).printBook(mockPrintStream);
        verify(mockCheckedOutBook, times(1)).printBook(mockPrintStream);
    }

}