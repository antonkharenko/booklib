package com.antonkharenko.booklib.providers.book.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Describes book search result representation returned by the call to Google Books API.
 *
 * @author Anton Kharenko
 * @see <a href="https://developers.google.com/books/docs/v1/reference/volumes/list">
 * https://developers.google.com/books/docs/v1/reference/volumes/list</a>
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeList {

    @JsonProperty
    private String kind;

    @JsonProperty
    private Integer totalItems;

    @JsonProperty
    private List<Volume> items;

    private VolumeList() {
        // Jackson deserialization
    }

    /**
     * Resource type.
     */
    public String getKind() {
        return kind;
    }

    /**
     * Total number of volumes found. This might be greater than the number of volumes returned in
     * this response if results have been paginated.
     */
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * A list of volumes.
     */
    public List<Volume> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "VolumeList{" +
                "kind='" + kind + '\'' +
                ", totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
