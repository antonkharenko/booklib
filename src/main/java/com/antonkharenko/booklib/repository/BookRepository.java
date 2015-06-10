package com.antonkharenko.booklib.repository;

import com.antonkharenko.booklib.domain.Book;
import com.google.common.base.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Anton Kharenko
 */
public interface BookRepository extends MongoRepository<Book, String> {

	Optional<Book> findByIdAndUserId(String bookId, String userId);

	List<Book> findByUserId(String userId);

}
