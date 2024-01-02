package com.ag2024.dbudim.smoke;

import com.ag2024.dbudim.fixtures.FixtureAdminView;
import com.dbudim.ag2024.client.models.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BooksPageTest extends FixtureAdminView {

    private Book book;

    @BeforeMethod
    public void setUp() throws IOException {
        book = client.booksService.createBook(new Book(randomAlphabetic(10), randomAlphabetic(10))).execute().body();
        generatedData.add(book);
        pages.booksPage.open();
    }

    @Test
    public void checkElementsSoft() {
        pages.booksPage.openBook(book.name);
        pages.manageBookPage.assertAllElementsPresentSoft();
        bookStack.books.doSome();
    }

    @Test
    public void checkElementsHard() {
        pages.booksPage.openBook(book.name);
        pages.manageBookPage.assertAllElementsPresent();
    }

}
