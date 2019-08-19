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
    private BufferedReader mockBufferedReader;
    private BibliotecaAppView mockBibliotecaAppView;
    private BibliotecaAppView bibliotecaAppView;
    private BibliotecaApp realAppWithMockParameters;
    private BibliotecaApp appWithBelovedInLibrary;
    private BibliotecaApp appWithTwilightInLibrary;
    private BibliotecaApp appWithMockBook;
    private BibliotecaApp appWithTwoMockBooks;
    private BibliotecaApp appWithMockMovie;
    private BibliotecaApp appWithRealBibliotecaView;
    private Book beloved;
    private Book mockBook;
    private Book mockBook2;
    private Movie twilight;
    private Movie mockMovie;

    @Before
    public void setUp() {

        mockBufferedReader = mock(BufferedReader.class);
        mockPrintStream = mock(PrintStream.class);
        mockLibrary = mock(Library.class);
        mockBibliotecaAppView = mock(BibliotecaAppView.class);
        realAppWithMockParameters = new BibliotecaApp(mockLibrary, mockBufferedReader, mockBibliotecaAppView);

        ArrayList<Book> belovedBookList = new ArrayList<Book>();
        beloved = new Book("Beloved", "Toni Morrison", "2010");
        belovedBookList.add(beloved);
        ArrayList<Movie> stubMovieList = new ArrayList<Movie>();
        Library libraryWithBeloved = new Library(mockPrintStream, belovedBookList, stubMovieList);
        appWithBelovedInLibrary = new BibliotecaApp(libraryWithBeloved, mockBufferedReader, mockBibliotecaAppView);

        ArrayList<Book> bookList = new ArrayList<Book>();
        mockBook = mock(Book.class);
        bookList.add(mockBook);
        Library libraryWithOneMockBook = new Library(mockPrintStream, bookList, stubMovieList);
        appWithMockBook = new BibliotecaApp(libraryWithOneMockBook, mockBufferedReader, mockBibliotecaAppView);

        ArrayList<Book> booklist2 = new ArrayList<Book>();
        mockBook2 = mock(Book.class);
        booklist2.add(mockBook);
        booklist2.add(mockBook2);
        Library libraryWithTwoMockBooks = new Library(mockPrintStream, booklist2, stubMovieList);
        appWithTwoMockBooks = new BibliotecaApp(libraryWithTwoMockBooks, mockBufferedReader, mockBibliotecaAppView);

        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        bibliotecaAppView = new BibliotecaAppView(printStream);
        appWithRealBibliotecaView = new BibliotecaApp(libraryWithTwoMockBooks, mockBufferedReader, bibliotecaAppView);

        ArrayList<Movie> movieListWithTwilight = new ArrayList<Movie>();
        twilight = new Movie("Twilight", "some person", "2010", 2);
        movieListWithTwilight.add(twilight);
        ArrayList<Book> stubBookList = new ArrayList<Book>();
        Library libraryWithTwilight = new Library(mockPrintStream, stubBookList, movieListWithTwilight);
        appWithTwilightInLibrary = new BibliotecaApp(libraryWithTwilight, mockBufferedReader,mockBibliotecaAppView);

        ArrayList<Movie> movieListWithMockMovie = new ArrayList<Movie>();
        mockMovie = mock(Movie.class);
        Library libraryWithOneMockMovie = new Library(mockPrintStream, stubBookList, movieListWithMockMovie);
        appWithMockMovie = new BibliotecaApp(libraryWithOneMockMovie, mockBufferedReader, mockBibliotecaAppView);

    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockBibliotecaAppView, times(1)).displayWelcomeMessage();
    }

    @Test
    public void shouldDisplayOptionsAfterWelcomeMessage() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("q");
        realAppWithMockParameters.start();
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
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        appWithMockBook.start();

        // check if print book is called on mock book one time
        verify(mockBook).printBook(mockPrintStream);
    }


    @Test
    public void shouldPrintTwoBooksWhenOption1IsSelectedAndThereAreTwoBooksInTheLibrary() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        appWithTwoMockBooks.start();

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
    public void shouldDisplayBookListWhen1IsSelectedFromOptions() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockLibrary).printBooklist();
    }

    @Test
    public void shouldInformUserOfInValidInputWhenInputIsNotValidMenuOption() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("Not A Valid Option").thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockBibliotecaAppView, atLeastOnce()).printInvalidInputMessage();
    }
    @Test
    public void shouldQuitApplicationWhenUserInputsQFromOptionMenu() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockBibliotecaAppView).showQuitMessage();
    }

    @Test
    public void shouldDisplayUserOptionToCheckOutBookInMenu() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("q");
        appWithRealBibliotecaView.start();
        String output = outputStream.toString();
        assertThat(output, containsString("1 - List of Books"));
        assertThat(output, containsString("2 - Checkout Book"));
        assertThat(output, containsString("3 - Return Book"));
        assertThat(output, containsString("4 - List of Movies"));
        assertThat(output, containsString("5 - Checkout Movie"));
        assertThat(output, containsString("q - Leave the Library"));
    }

    @Test
    public void shouldPromptUserToEnterABookTitleWhenOption2FromMenuIsSelected() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockBibliotecaAppView).displayCheckOutBookInstructions();

    }

    // QUESTION: should everything that can possibly be a mock be made one?
    // Protocol on writing a test that cannot compile?
    // Should this actually test if the code correctly notifies the user?
    @Test
    public void shouldCheckOutBookWithNotificationToUserIfUserSuccessfullyChecksOutBook() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("Beloved").thenReturn("q");
        appWithBelovedInLibrary.start();
        assertTrue(beloved.getIsCheckedOut());
        verify(mockBibliotecaAppView).displayCheckOutBookSuccessful();
    }

    // Question: is it better to write a test for the behavior of what the user should interact with (actual text) or mocks (test behavior more)
    @Test
    public void shouldDisplayErrorMessageIfUserInputsBookTitleToCheckOutThatIsNotAvailable() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("Another Book").thenReturn("q");
        appWithBelovedInLibrary.start();
        verify(mockBibliotecaAppView).displayCheckOutBookNotSuccessful();
    }

    @Test
    public void shouldNotCallPrintBookInPrintBookListMethodOnBookWhenBookIsCheckedOut() throws IOException {
        when(mockBook.getIsCheckedOut()).thenReturn(true);
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        appWithMockBook.start();
        verify(mockBook, never()).printBook(mockPrintStream); // must never be called
   }

    @Test
    public void shouldPromptUserToEnterABookTitleWhenOption3FromMenuIsSelected() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("3").thenReturn("Some Title").thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockBibliotecaAppView).displayReturnBookInstructions();
    }

    @Test
    public void shouldReturnBookWithNotificationToUserIfUserSuccessfullyReturnsBook() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("3").thenReturn("Beloved").thenReturn("q");
        appWithBelovedInLibrary.start();
        String output = outputStream.toString();

        // Successfully returns book
        assertFalse(beloved.getIsCheckedOut());

        // Successfully Notifies User
        verify(mockBibliotecaAppView).displayReturnBookSuccessful();
    }


    // BEGIN TESTS FOR MOVIES

    @Test
    public void shouldDisplayPrintMoviesWhenUserEntersOption4AtTheMenu() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("4").thenReturn("q");
        realAppWithMockParameters.start();
        verify(mockLibrary).printMovieList();
    }

    @Test
    public void shouldCheckOutBookWithNotificationToUserIfUserChecksOutBook() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("5").thenReturn("Twilight").thenReturn("q");
        appWithTwilightInLibrary.start();
        assertTrue(twilight.getIsCheckedOut());
        verify(mockBibliotecaAppView).displayCheckOutMovieSuccessful();
    }
    @Test
    public void shouldDisplayErrorMessageIfUserInputsMovieTitleToCheckOutThatIsNotAvailable() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("5").thenReturn("Another Movie").thenReturn("q");
        appWithTwilightInLibrary.start();
        verify(mockBibliotecaAppView).displayCheckOutMovieNotSuccessful();
    }

    @Test
    public void shouldNotCallPrintMovieInPrintMovieListMethodOnBookWhenBookIsCheckedOut() throws IOException {
        when(mockMovie.getIsCheckedOut()).thenReturn(true);
        when(mockBufferedReader.readLine()).thenReturn("5").thenReturn("q");
        appWithMockMovie.start();
        verify(mockMovie, never()).printMovie(mockPrintStream); // must never be called
    }
}