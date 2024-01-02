package com.ag2024.dbudim.some_examples.preconditions_fixtures.smell;

import com.ag2024.dbudim.fixtures.FixtureAdminView;
import com.ag2024.pages.Pages;
import com.dbudim.ag2024.client.BookStack;
import com.dbudim.ag2024.client.BookStackRestClient;
import com.dbudim.ag2024.client.models.Book;
import com.dbudim.ag2024.exceptions.BookNotFoundException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.dbudim.ag2024.utils.ExecutionUtils.execute;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CreateBookActions {

    private static final String EMAIL = "admin@admin.com";
    private static final String PASS = "password";
    private static final String BOOK_NAME = randomAlphabetic(10);

    private Book book;
    private BookStackRestClient client = new BookStackRestClient();
    private BookStack bookStack = new BookStack(client);
    private Pages pages = new Pages();

    @BeforeClass
    public void loginByAdmin() {
        pages.loginPage.open()
                .login(EMAIL, PASS);
    }

    @BeforeMethod
    public void openBooks() {
        pages.booksPage.open();
    }

    @Test
    public void createBookTest() {
        pages.booksPage.createBook(BOOK_NAME);
        execute(client.booksService.getBooks()).body().data
                .stream()
                .filter(b -> b.name.equals(BOOK_NAME))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(BOOK_NAME));
    }

}
