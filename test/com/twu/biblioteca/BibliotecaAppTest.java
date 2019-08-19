package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
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
        app = new BibliotecaApp(mockLibrary, bufferedReader, mockBibliotecaAppView);

    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();
        verify(mockBibliotecaAppView, times(1)).displayWelcomeMessage();
    }

    @Test
    public void shouldDisplayOptionsAfterWelcomeMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn("q");
        app.start();
        verify(mockBibliotecaAppView, atLeastOnce()).displayOptionMenu();
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
        app = new BibliotecaApp(libWithMockBook, bufferedReader, mockBibliotecaAppView);

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
        app = new BibliotecaApp(libWithMockBook, bufferedReader, mockBibliotecaAppView);

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
        BibliotecaApp app = new BibliotecaApp(mockLibrary, bufferedReader, mockBibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();
        verify(mockLibrary).printBooklist();
    }

    @Test
    public void shouldInformUserOfInValidInputWhenInputIsNot1Or2OrQ() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        PrintStream mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Library mockLibrary = mock(Library.class);
        BibliotecaAppView mockBibliotecaAppView = mock(BibliotecaAppView.class);
        BibliotecaApp app = new BibliotecaApp(mockLibrary, bufferedReader, mockBibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("Not A Valid Option").thenReturn("q");

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
        BibliotecaApp app = new BibliotecaApp(mockLibrary, bufferedReader, mockBibliotecaAppView);

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
        BibliotecaApp app = new BibliotecaApp(lib, bufferedReader, bibliotecaAppView);

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
        BibliotecaApp app = new BibliotecaApp(lib, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("2").thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();
        assertThat(output, containsString("Please Enter The Title of the Book To Checkout: "));
    }

    // QUESTION: should everything that can possibly be a mock be made one?
    // Protocol on writing a test that cannot compile?
    // Should this actually test if the code correctly notifies the user?

    @Test
    public void shouldCheckOutBookWithNotificationToUserIfUserSuccessfullyChecksOutBook() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book beloved = new Book("Beloved", "Toni Morrison", "2010");
        bookList.add(beloved);

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("2").thenReturn("Beloved").thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();

        assertTrue(beloved.getIsCheckedOut());
        assertThat(output, containsString("Thank you! Enjoy the Book!"));
    }

    // Refactoring Idea: use mock bibliotecaAppView and check if .displayCheckOutNotSuccessful() is called
    // Question: is it better to write a test for the behavior of what the user should interact with (actual text) or mocks (test behavior more)
    @Test
    public void shouldDisplayErrorMessageIfUserInputsTitleToCheckOutThatIsNotAvailable() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book beloved = new Book("Beloved", "Toni Morrison", "2010");
        bookList.add(beloved);

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("2").thenReturn("Another Book").thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();
        assertThat(output, containsString("Sorry that book is not available."));

    }
    @Test
    public void shouldNotCallPrintBookInPrintBookListMethodOnBookWhenBookIsCheckedOut() throws IOException {
        // make app with library with one mock book in it

        ArrayList<Book> bookList = new ArrayList<Book>();
        Book mockCheckedOutBook = mock(Book.class);
        bookList.add(mockCheckedOutBook);
        Library lib = new Library(mockPrintStream, bookList);
        OutputStream mockOutputStream = mock(OutputStream.class);
        app = new BibliotecaApp(lib, bufferedReader, mockBibliotecaAppView);

        when(mockCheckedOutBook.getIsCheckedOut()).thenReturn(true);

        when(bufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();

        verify(mockCheckedOutBook, never()).printBook(mockPrintStream); // must never be called
   }

    @Test
    public void shouldDisplayUserOptionToReturnBookInMenu() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();
        assertThat(output, containsString("3 - Return Book"));
    }

    @Test
    public void shouldPromptUserToEnterABookTitleWhenOption3FromMenuIsSelected() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        PrintStream mockPrintStream = mock(PrintStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Library mockLibrary = mock(Library.class);
        BibliotecaAppView mockBibliotecaAppView = mock(BibliotecaAppView.class);
        BibliotecaApp app = new BibliotecaApp(mockLibrary, bufferedReader, mockBibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("3").thenReturn("Some Title").thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).displayReturnBookInstructions();
    }

    @Test
    public void shouldReturnBookWithNotificationToUserIfUserSuccessfullyReturnsBook() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book beloved = new Book("Beloved", "Toni Morrison", "2010");
        beloved.checkOutBook();
        bookList.add(beloved);

        BufferedReader bufferedReader = mock(BufferedReader.class);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteOutputStream);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);
        Library lib = new Library(printStream, bookList);
        BibliotecaApp app = new BibliotecaApp(lib, bufferedReader, bibliotecaAppView);

        when(bufferedReader.readLine()).thenReturn("3").thenReturn("Beloved").thenReturn("q");

        app.start();

        String output = byteOutputStream.toString();

        // Successfully returns book
        assertFalse(beloved.getIsCheckedOut());

        // Successfully Notifies User
        assertThat(output, containsString("Thank you for returning the book!"));
    }
}