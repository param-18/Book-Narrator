package com.haryanvis.booknarrator.model;

public class Book {
    private  int bID ;
    private String name , genre , language , srcLink ;
    private int length , rating;
    private String coverImage;

    //default for Getting Purposes
    public Book(){}

    //custom for Setting and preventing from NullException
    public Book(int bID, String name, String genre, String language, String srcLink, int length, int rating , String coverImage) {
        this.bID = bID;
        this.name = name;
        this.genre = genre;
        this.language = language;
        this.srcLink = srcLink;
        this.length = length;
        this.rating = rating;
        this.coverImage = coverImage;
    }

    public int getbID() {
        return bID;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getSrcLink() {
        return srcLink;
    }

    public int getLength() {
        return length;
    }

    public int getRating() {
        return rating;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setbID(int bID) {
        this.bID = bID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSrcLink(String srcLink) {
        this.srcLink = srcLink;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
