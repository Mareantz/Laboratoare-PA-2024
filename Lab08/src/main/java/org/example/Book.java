package org.example;

import java.util.Date;
import java.util.List;
public class Book {
    private int id;
    private String title;
    private List<String> authors;
    private String language;
    private Date publicationDate;
    private int numberOfPages;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<String> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<String> authors)
    {
        this.authors = authors;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public Date getPublicationDate()
    {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate)
    {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages)
    {
        this.numberOfPages = numberOfPages;
    }

}