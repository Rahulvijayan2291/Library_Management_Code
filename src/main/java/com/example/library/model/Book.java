package com.example.library.model;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String ISBN;
    @NonNull
    private String title;
    private String author;
    private String description;
    private String genre;
    private int publicationYear;
    private int copiesAvailable;
    private String publisher;
    private String coverImageUrl;


    public Book() {
    }

    public Book(String ISBN, String title, String author, String description, String genre, int publicationYear, int copiesAvailable, String publisher, String coverImageUrl) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
        this.publisher = publisher;
        this.coverImageUrl = coverImageUrl;
    }

    


    public Book(int id, String ISBN, String title, String author, String description, String genre, int publicationYear,
            int copiesAvailable, String publisher, String coverImageUrl) {
        this.id=id;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
        this.publisher = publisher;
        this.coverImageUrl = coverImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("ISBN")
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", description="
                + description + ", genre=" + genre + ", publicationYear=" + publicationYear + ", copiesAvailable="
                + copiesAvailable + ", publisher=" + publisher + ", coverImageUrl=" + coverImageUrl + "]";
    }


}
