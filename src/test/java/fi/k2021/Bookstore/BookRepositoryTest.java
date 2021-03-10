package fi.k2021.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.k2021.Bookstore.domain.Book;
import fi.k2021.Bookstore.domain.BookRepository;
import fi.k2021.Bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void findByAuthorBookShouldReturnListOfBooks() {
		List<Book> books = bookRepository.findByAuthor("J.R.R. Tolkien");
		assertThat(books).hasSize(2);
		assertThat(books.get(0).getTitle()).isEqualTo("Lord of the rings");
	}

	@Test
	public void findByAuthorShouldReturnSize() {
		List<Book> books = bookRepository.findByAuthor("J.R.R. Tolkien");
		assertThat(books).hasSize(3);
		
	}
	
	@Test 
	public void deleteBook() {
		 
		List<Book> books = bookRepository.findByTitle("The Hobbit");
		bookRepository.deleteById(books.get(0).getId());
		books= bookRepository.findByTitle("The Hobbit");
		assertThat(books).hasSize(0);
	}


	@Test
	public void insertNewBook() {
		Book book = new Book("Hennig Mankell TEST", "Kasvoton kuolema TEST","B1234",
				2021, 39.90, categoryRepository.findByName("Crime").get(0));
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

}
