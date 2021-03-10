package fi.k2021.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.k2021.Bookstore.domain.Book;
import fi.k2021.Bookstore.domain.BookRepository;
import fi.k2021.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bRepository; 
	
	@Autowired
	private CategoryRepository cRepository;

    @RequestMapping(value= {"/", "/booklist"})
    public String bookList(Model model) {	
        model.addAttribute("books", bRepository.findAll());
        return "booklist";
    }
    
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
    	return bRepository.findById(bookId);
    }
	
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", cRepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bRepository.save(book);
        return "redirect:booklist";
    }    

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
    	bRepository.deleteById(bookId);
        return "redirect:../booklist";
    }     
    
    @RequestMapping(value ="/edit/{id}")
    public String addBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book", bRepository.findById(bookId));
    	model.addAttribute("categories", cRepository.findAll());
    	return "editbook";
    }
}
