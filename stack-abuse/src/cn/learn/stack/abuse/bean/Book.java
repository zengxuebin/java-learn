package cn.learn.stack.abuse.bean;

/**
 * @author zengxuebin
 * @since 2024/11/22 08:51
 */
public class Book {

    private String id;
    private String name;
    private String author;
    private long pageNumber;
    private long publishedYear;


    public Book() {
    }

    public Book(String id, String name, String author, long pageNumber, long publishedYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pageNumber = pageNumber;
        this.publishedYear = publishedYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(long publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pageNumber=" + pageNumber +
                ", publishedYear=" + publishedYear +
                '}';
    }
}
