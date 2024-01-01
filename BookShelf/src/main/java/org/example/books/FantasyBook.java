package org.example.books;

public class FantasyBook extends Book{

    FantasyBook(){
        this.genre= "Fantasy";
    }

    @Override
    public void printContent() {
        System.out.println(this.getISBN()+":"+this.getGenre()+","+this.getPages()+"pp.");
    }

}