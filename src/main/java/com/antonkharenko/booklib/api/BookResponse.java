package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public class BookResponse {

	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("subtitle")
	private String subtitle;

	@JsonProperty("authors")
	private List<String> authors;

	@JsonProperty("publisher")
	private String publisher;

	@JsonProperty("published_date")
	private String publishedDate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("cover_images")
	private ImageLinks coverImages;

	@JsonProperty("industry_identifiers")
	private List<IndustryIdentifier> industryIdentifiers;

	@JsonProperty("page_count")
	private Integer pageCount;

	@JsonProperty("categories")
	private List<String> categories;

	@JsonProperty("main_category")
	private String mainCategory;

	@JsonProperty("language")
	private String language;

	@SuppressWarnings("unused")
	private BookResponse() {
		// Jackson deserialization
	}

	private BookResponse(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.subtitle = builder.subtitle;
		this.authors = builder.authors;
		this.publisher = builder.publisher;
		this.publishedDate = builder.publishedDate;
		this.description = builder.description;
		this.coverImages = builder.coverImages;
		this.industryIdentifiers = builder.industryIdentifiers;
		this.pageCount = builder.pageCount;
		this.categories = builder.categories;
		this.mainCategory = builder.mainCategory;
		this.language = builder.language;
	}

	public static BookResponse.Builder newBuilder() {
		return new Builder();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public ImageLinks getCoverImages() {
		return coverImages;
	}

	public List<IndustryIdentifier> getIndustryIdentifiers() {
		return industryIdentifiers;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public List<String> getCategories() {
		return categories;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BookResponse book = (BookResponse) o;
		return Objects.equals(id, book.id) &&
				Objects.equals(title, book.title) &&
				Objects.equals(subtitle, book.subtitle) &&
				Objects.equals(authors, book.authors) &&
				Objects.equals(publisher, book.publisher) &&
				Objects.equals(publishedDate, book.publishedDate) &&
				Objects.equals(description, book.description) &&
				Objects.equals(coverImages, book.coverImages) &&
				Objects.equals(industryIdentifiers, book.industryIdentifiers) &&
				Objects.equals(pageCount, book.pageCount) &&
				Objects.equals(categories, book.categories) &&
				Objects.equals(mainCategory, book.mainCategory) &&
				Objects.equals(language, book.language);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(id, title, subtitle, authors, publisher, publishedDate, description, coverImages, industryIdentifiers, pageCount,
						categories, mainCategory, language);
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
				'}';
	}

	public static final class Builder {

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

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder subtitle(String subtitle) {
			this.subtitle = subtitle;
			return this;
		}

		public Builder authors(List<String> authors) {
			this.authors = authors;
			return this;
		}

		public Builder publisher(String publisher) {
			this.publisher = publisher;
			return this;
		}

		public Builder publishedDate(String publishedDate) {
			this.publishedDate = publishedDate;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder coverImages(ImageLinks coverImages) {
			this.coverImages = coverImages;
			return this;
		}

		public Builder industryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
			this.industryIdentifiers = industryIdentifiers;
			return this;
		}

		public Builder pageCount(Integer pageCount) {
			this.pageCount = pageCount;
			return this;
		}

		public Builder categories(List<String> categories) {
			this.categories = categories;
			return this;
		}

		public Builder mainCategory(String mainCategory) {
			this.mainCategory = mainCategory;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public BookResponse build() {
			return new BookResponse(this);
		}
	}
}
