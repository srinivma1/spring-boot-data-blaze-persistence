package de.michlb.sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.michlb.sample.domain.Book;
import de.michlb.sample.domain.BookCategory;
import de.michlb.sample.service.BookCategoryService;
import de.michlb.sample.view.BookCategoryView;


/**
 * Created by mbart on 28.02.2016.
 */
@Controller
@RequestMapping("/books")
public class IndexController {

	@Autowired

	private BookCategoryService personService;



	

	@RequestMapping("/all")
	public String showIndex(Model model) {
		List<BookCategoryView> personList = personService.loadAll();

		model.addAttribute("personList", personList);

		return "index"; // return index.html Template
	}

	@RequestMapping(value="/save", method = RequestMethod.GET)
	public void savePerson( Model model) {
		BookCategory categoryA = new BookCategory();
		categoryA.setName("Category A");
	     ArrayList<Book> bookAs = new ArrayList<Book>();
	     Book b = new Book();
	     b.setName("BookA1");
	     b.setBookCategory(categoryA);
	     Book c = new Book();
	     c.setName("BookA2");
	     c.setBookCategory(categoryA);
	     Book d = new Book();
	     d.setName("BookA3");
	     d.setBookCategory(categoryA);
	     bookAs.add(b);
	     bookAs.add(d);
	     bookAs.add(c);
	    categoryA.setBooks(bookAs);
	 personService.saveBookCategory(categoryA);
		
		 // return index.html Template
	}
	
	@RequestMapping(value="/deleteList", method = RequestMethod.GET)
	public String deleteBookCategoryList( Model model) {
		List<Integer> idList = new ArrayList();
		idList.add(7);
		//idList.add(6);
	 personService.deleteBookCategoryList(idList);
	

		return "index"; // return index.html Template
	}
	
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<BookCategoryView>> showBookCategoryOrderById(@RequestParam("id") Integer id,@RequestParam("before") String before,@RequestParam("after") String after) {
		boolean beforeKeyset = false;
		boolean afterKeyset = false;
		
		if(("yes").equalsIgnoreCase(before)){
				beforeKeyset = true;
				
		} else if(("yes").equalsIgnoreCase(after)){
			afterKeyset = true;
			
	}
		
		
		List<BookCategoryView> personList = personService.loadBookCategoryOrderById(beforeKeyset, afterKeyset, id);
return ResponseEntity.ok().body(personList);
	}
	
}
