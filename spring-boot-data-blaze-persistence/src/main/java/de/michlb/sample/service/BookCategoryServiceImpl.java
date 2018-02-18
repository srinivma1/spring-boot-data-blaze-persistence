package de.michlb.sample.service;

import de.michlb.sample.domain.BookCategory;
import de.michlb.sample.repositories.BookCategoryDAO;
import de.michlb.sample.repositories.BookCategoryRepository;
import de.michlb.sample.view.BookCategoryView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

/**
 * Created by mbart on 28.02.2016.
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

  @Autowired
  private BookCategoryRepository bookCategoryRepository;
  
  @Autowired
  private BookCategoryDAO bookCategoryDao;

  @Override
  public List<BookCategoryView> loadAll() {
    return bookCategoryDao.getAllBookCategories();
  }

@Override
public BookCategory saveBookCategory(BookCategory bookCategory) {
	// TODO Auto-generated method stub
	return bookCategoryRepository.saveAndFlush(bookCategory);
	
}
@Transactional
@Override
public void deleteBookCategoryList(List<Integer> ids) {
	bookCategoryRepository.deleteByIdIn(ids);
	
}

@org.springframework.transaction.annotation.Transactional(readOnly=true)
@Override
public Set<BookCategory> findBookCategoryByName(String name) {
	// TODO Auto-generated method stub
	return bookCategoryRepository.findByBookCategoryName(name);
}

@Override
public List<BookCategoryView> loadBookCategoryOrderById(boolean before, boolean after, Integer lastKeyset) {
	

	// TODO Auto-generated method stub
	return bookCategoryDao.getBookCategoriesOrderById(before, after, lastKeyset);
}
}
