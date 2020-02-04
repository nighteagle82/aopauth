package cn.ne.aopauth.utils.link.testcase;

import cn.ne.aopauth.utils.link.Link;
import cn.ne.aopauth.utils.link.LinkImple;

public class Student {

    private Link<Book> books = new LinkImple<>();

    public void buy(Book book){
        this.books.add(book);
    }

    public void give(Book book) {
        this.books.remove(book);
    }

    public Link<?> search(String keyword) {
        Link<Book> result = new LinkImple<>();
        Object[] array = this.books.toArray();
        for (Object temp : array) {
            Book book = (Book) temp;
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)){
                result.add(book);
            }
        }
        return result;
    }

    public Link<?> getBooks(){
        return this.books;
    }

}
