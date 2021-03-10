package fi.k2021.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.k2021.Bookstore.domain.*;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bRepository, CategoryRepository cRepository, AppUserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of books");
			cRepository.save(new Category("Fantasy"));
			cRepository.save(new Category("Horror"));
			cRepository.save(new Category("Crime"));
			
			bRepository.save(new Book("Lord of the rings", "J.R.R. Tolkien", "403857f", 1953, 45.90, cRepository.findByName("Fantasy").get(0)));	
			bRepository.save(new Book("The Hobbit", "J.R.R. Tolkien", "403857f", 1948, 35.90, cRepository.findByName("Fantasy").get(0)));
			
			AppUser user1 = new AppUser("user", "$2a$10$y.NgWtx7SlKaomvWKGKNEe9js4ELZhMKkIPpQWwG/0JUHtRlgJDoS", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$uJ.HaKB1.nUFZOwFw1bid.bT49J2ik/1pp1DWYiowEmu9jz0IR82S", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : bRepository.findAll()) {
				log.info(book.toString());
			
			}

		};
	};
}
