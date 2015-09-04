package com.antonkharenko.booklib.domain;

import com.antonkharenko.booklib.api.ImageLinks;
import com.antonkharenko.booklib.api.IndustryIdentifier;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Anton Kharenko
 */
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private String subtitle;

    private List<String> authors;

    private String publisher;

    private String publishedDate;

    private String description;

    private ImageLinks coverImages;

    private List<IndustryIdentifier> industryIdentifiers;

    private Integer pageCount;

    private List<String> categories;

    private String mainCategory;

    private String language;

    private String userId;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getCoverImages() {
        return coverImages;
    }

    public void setCoverImages(ImageLinks coverImages) {
        this.coverImages = coverImages;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", coverImages=" + coverImages +
                ", industryIdentifiers=" + industryIdentifiers +
                ", pageCount=" + pageCount +
                ", categories=" + categories +
                ", mainCategory='" + mainCategory + '\'' +
                ", language='" + language + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
