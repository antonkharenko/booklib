package com.antonkharenko.booklib.providers.book.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Volume represents information that Google Books API returns about a book or a magazine. It contains metadata,
 * such as title and author, as well as personalized data, such as whether or not it has been purchased.
 *
 * Volume fields that are available in LITE projection are noted below.
 *
 * @author Anton Kharenko
 * @see <a href="https://developers.google.com/books/docs/v1/reference/volumes">
 *     https://developers.google.com/books/docs/v1/reference/volumes</a>
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Volume {

	@JsonProperty
	private String kind;

	@JsonProperty
	private String id;

	@JsonProperty
	private String etag;

	@JsonProperty
	private String selfLink;

	@JsonProperty
	private VolumeInfo volumeInfo;

	private Volume() {
		// Jackson deserialization
	}

	/** Resource type for a volume. (In LITE projection.) */
	public String getKind() {
		return kind;
	}

	/** Unique identifier for a volume. (In LITE projection.) */
	public String getId() {
		return id;
	}

	/** Opaque identifier for a specific version of a volume resource. (In LITE projection) */
	public String getEtag() {
		return etag;
	}

	/** URL to this resource. (In LITE projection.) */
	public String getSelfLink() {
		return selfLink;
	}

	/** General volume information. */
	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}

	@Override
	public String toString() {
		return "Volume{" +
				"kind='" + kind + '\'' +
				", id='" + id + '\'' +
				", etag='" + etag + '\'' +
				", selfLink='" + selfLink + '\'' +
				", volumeInfo=" + volumeInfo +
				'}';
	}

}
