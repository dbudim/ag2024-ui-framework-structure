package com.ag2024.dbudim.some_examples.explicit_assign;

import com.ag2024.dbudim.fixtures.FixtureAdminView;
import com.dbudim.ag2024.client.models.Book;
import org.testng.annotations.Test;

import java.util.List;

import static com.dbudim.ag2024.utils.ExecutionUtils.execute;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertListNotContains;

public class Smell extends FixtureAdminView {

    @Test
    public void renameBookTest() {
        createBookSmell(randomAlphabetic(19));
        pages.booksPage.open().openBook(book.name);
        String newName = randomAlphabetic(5);
        pages.manageBookPage.rename(newName);

        Book renamed = execute(client.booksService.getBook(book.id)).body();
        assertEquals(renamed.name, newName, "books wasn't renamed");
    }

    @Test
    public void deleteBookTest() {
        createBookSmell(randomAlphabetic(19));
        pages.booksPage.open().openBook(book.name);
        pages.manageBookPage.delete();
        List<Book> books = execute(client.booksService.getBooks()).body().data;
        assertListNotContains(books, b -> b.name.equals(book.name), "book wasn't removed");
    }


}
