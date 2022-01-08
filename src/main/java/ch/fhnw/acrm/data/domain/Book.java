package ch.fhnw.acrm.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Please provide a name.")
    private String name;
    @NotEmpty(message = "Please provide a price.")
    private float price;
    @NotEmpty(message = "Please provide an ISBN.")
    private String isbn;
    @NotEmpty(message = "Please provide an author.")
    private String author;
    private String description;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
