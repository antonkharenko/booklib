package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anton Kharenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLinks {

	@JsonProperty
	private String smallThumbnail;

	@JsonProperty
	private String thumbnail;

	@JsonProperty
	private String small;

	@JsonProperty
	private String medium;

	@JsonProperty
	private String large;

	@JsonProperty
	private String extraLarge;

	private ImageLinks() {
		// Jackson deserialization
	}

	/** Image link for small thumbnail size (width of ~80 pixels). */
	public String getSmallThumbnail() {
		return smallThumbnail;
	}

	/** Image link for thumbnail size (width of ~128 pixels). */
	public String getThumbnail() {
		return thumbnail;
	}

	/** Image link for small size (width of ~300 pixels). */
	public String getSmall() {
		return small;
	}

	/** Image link for medium size (width of ~575 pixels). */
	public String getMedium() {
		return medium;
	}

	/** Image link for large size (width of ~800 pixels). */
	public String getLarge() {
		return large;
	}

	/** Image link for extra large size (width of ~1280 pixels). */
	public String getExtraLarge() {
		return extraLarge;
	}

	@Override
	public String toString() {
		return "ImageLinks{" +
				"thumbnail='" + thumbnail + '\'' +
				", small='" + small + '\'' +
				", medium='" + medium + '\'' +
				", large='" + large + '\'' +
				", smallThumbnail='" + smallThumbnail + '\'' +
				", extraLarge='" + extraLarge + '\'' +
				'}';
	}
}
