package com.ag2024.dbudim.some_examples.preconditions_fixtures.well;

import com.ag2024.dbudim.fixtures.FixtureAdminView;
import com.dbudim.ag2024.exceptions.BookNotFoundException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.dbudim.ag2024.utils.ExecutionUtils.execute;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CreateBookActions extends FixtureAdminView {

    private static final String BOOK_NAME = randomAlphabetic(10);

    @BeforeClass
    public void prepareBook() {
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
