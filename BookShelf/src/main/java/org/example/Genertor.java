package org.example;
import org.example.books.Book;
import org.example.books.BookCash;
import java.util.Random;

public class Genertor {
    static Book createBook(){
        BookCash.loadCache();
        Random rand = new Random();
        int max=9999,min=1111;
        int id= rand.nextInt(3) + 1;
        Book b= BookCash.getBook(id);
        b.setISBN(rand.nextInt(max - min + 1) + min);
        b.setPages(rand.nextInt(max/90 - min/300 + 1) + min/300);
        return b;
    }
}
