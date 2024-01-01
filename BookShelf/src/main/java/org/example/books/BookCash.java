package org.example.books;

import java.util.HashMap;
import java.util.Map;

public class BookCash {
    private static Map <Integer, Book > bookMap = new HashMap <Integer, Book > ();

    public static Book getBook(int id) {
        Book toBeClonedBook = bookMap.get(id);
        return (Book) toBeClonedBook.clone();
    }

    public static void loadCache() {
        FantasyBook fb1 = new FantasyBook();
        bookMap.put(1, fb1);
        
        FictionBook fk1 = new FictionBook();
        bookMap.put(2, fk1);
        
        DetectiveBook dk1 = new DetectiveBook();
        bookMap.put(3, dk1);
    }
}
