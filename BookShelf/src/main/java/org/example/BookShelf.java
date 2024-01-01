package org.example;
import java.util.Iterator;
import org.example.books.Book;
import java.util.LinkedList;
import java.util.List;

public class BookShelf {
    private static BookShelf instance;
    private List<Book> storage;

    private BookShelf(){
        this.storage=new LinkedList<Book>();
    }

    public static BookShelf getInstance(){
        if(instance==null){
            instance=new BookShelf();
        }
        return instance;
    }

    public void printContent(){
        System.out.println("On my shelf:");
        for(Book b:storage){
            b.printContent();
        }
    }

    void addBook(Book b) {
        storage.add(b);
    }
    
    void delBook(int ISBN){
        Iterator<Book> iterator = storage.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (book.getISBN() == ISBN) {
                iterator.remove();
            }
        }
    }
}
