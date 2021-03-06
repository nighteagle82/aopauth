package cn.ne.aopauth.utils.link.testcase;

import java.util.Objects;

public class ProgramBook implements Book {

    private String title;
    private Double price;
    private String author;

    public ProgramBook() { }

    public ProgramBook(String title, double price, String author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "ProgramBook{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof ProgramBook)) return false;
        ProgramBook book = (ProgramBook) o;
        return Double.compare(book.price, price) == 0 &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, author);
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
