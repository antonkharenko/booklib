package com.antonkharenko.booklib.providers.book.google;

import com.antonkharenko.booklib.api.ImageLinks;
import com.antonkharenko.booklib.api.IndustryIdentifier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Anton Kharenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {

    @JsonProperty
    private String title;

    @JsonProperty
    private String subtitle;

    @JsonProperty
    private List<String> authors;

    @JsonProperty
    private String publisher;

    @JsonProperty
    private String publishedDate;

    @JsonProperty
    private String description;

    @JsonProperty
    private List<IndustryIdentifier> industryIdentifiers;

    @JsonProperty
    private Integer pageCount;

    @JsonProperty
    private String printType;

    @JsonProperty
    private List<String> categories;

    @JsonProperty
    private ImageLinks imageLinks;

    @JsonProperty
    private String language;

    @JsonProperty
    private String mainCategory;

    private VolumeInfo() {
        // Jackson deserialization
    }

    /**
     * Volume title. (In LITE projection.)
     */
    public String getTitle() {
        return title;
    }

    /**
     * Volume subtitle. (In LITE projection.)
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * The names of the authors and/or editors for this volume. (In LITE projection)
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * Publisher of this volume. (In LITE projection.)
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Date of publication. (In LITE projection.)
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * A synopsis of the volume. The text of the description is formatted in HTML and includes
     * simple formatting elements, such as b, i, and br tags. (in LITE projection)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Industry standard identifiers for this volume.
     */
    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    /**
     * Total number of pages.
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * Type of publication of this volume. Possible values are BOOK or MAGAZINE.
     */
    public String getPrintType() {
        return printType;
    }

    /**
     * A list of subject categories, such as "Fiction", "Suspense", etc.
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * A list of image links for all the sizes that are available. (in LITE projection)
     */
    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    /**
     * Best language for this volume (based on content). It is the two-letter ISO 639-1 code such as
     * 'fr', 'en', etc.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * The main category to which this volume belongs. It will be the category from the categories
     * list returned below that has the highest weight.
     */
    public String getMainCategory() {
        return mainCategory;
    }

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", industryIdentifiers=" + industryIdentifiers +
                ", pageCount=" + pageCount +
                ", printType='" + printType + '\'' +
                ", categories=" + categories +
                ", imageLinks=" + imageLinks +
                ", language='" + language + '\'' +
                ", mainCategory='" + mainCategory + '\'' +
                '}';
    }
}
