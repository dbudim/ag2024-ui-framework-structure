package com.ag2024.dbudim.some_examples.preconditions_fixtures.smell;

import com.ag2024.dbudim.fixtures.FixtureAdminView;
import com.ag2024.pages.Pages;
import com.dbudim.ag2024.client.BookStack;
import com.dbudim.ag2024.client.BookStackRestClient;
import com.dbudim.ag2024.client.models.Book;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.dbudim.ag2024.utils.ExecutionUtils.execute;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertListNotContains;

public class ManageBookActionsExample {

    private static final String EMAIL = "admin@admin.com";
    private static final String PASS = "password";

    private Book book;
    private BookStackRestClient client = new BookStackRestClient();
    private BookStack bookStack = new BookStack(client);
    private Pages pages = new Pages();
    private static final String BOOK_NAME = randomAlphabetic(10);

    @BeforeClass
    public void loginByAdmin() {
        pages.loginPage.open()
                .login(EMAIL, PASS);
    }

    @Test
    public void renameBookTest() {
        book = bookStack.books.createBook(randomAlphabetic(10));
        pages.booksPage.open()
                .openBook(book.name);

        String newName = randomAlphabetic(5);
        pages.manageBookPage.rename(newName);

        book = execute(client.booksService.getBook(book.id)).body();
        assertEquals(book.name, newName, "books wasn't renamed");

        execute(client.booksService.deleteBook(book.id));
    }

    @Test
    public void deleteBookTest() {
        book = bookStack.books.createBook(randomAlphabetic(10));
        pages.booksPage.open()
                .openBook(book.name);

        pages.manageBookPage.delete();
        List<Book> books = execute(client.booksService.getBooks()).body().data;
        assertListNotContains(books, b -> b.name.equals(book.name), "book wasn't removed");
    }

}
