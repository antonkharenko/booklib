package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Anton Kharenko
 */
@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = BookResponse.class)
@JsonDeserialize(as = BookResponse.class)
public interface IBookResponse {

	String getId();

	@Nullable
	String getTitle();

	@Nullable
	String getSubtitle();

	@Nullable
	List<String> getAuthors();

	@Nullable
	String getPublisher();

	@Nullable
	@JsonProperty("published_date")
	String getPublishedDate();

	@Nullable
	String getDescription();

	@Nullable
	@JsonProperty("cover_images")
	ImageLinks getCoverImages();

	@Nullable
	@JsonProperty("industry_identifiers")
	List<IndustryIdentifier> getIndustryIdentifiers();

	@Nullable
	@JsonProperty("page_count")
	Integer getPageCount();

	@Nullable
	List<String> getCategories();

	@Nullable
	@JsonProperty("main_category")
	String getMainCategory();

	@Nullable
	String getLanguage();

}
