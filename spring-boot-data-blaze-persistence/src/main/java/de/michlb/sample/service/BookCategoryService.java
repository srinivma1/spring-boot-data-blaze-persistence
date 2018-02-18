package de.michlb.sample.service;

import de.michlb.sample.domain.BookCategory;
import de.michlb.sample.view.BookCategoryView;

import java.util.List;
import java.util.Set;

/**
 * Created by mbart on 28.02.2016.
 */
public interface BookCategoryService {
 List<BookCategoryView> loadAll();
  BookCategory  saveBookCategory(BookCategory bookCategory);
  void deleteBookCategoryList(List<Integer> ids);
  Set<BookCategory> findBookCategoryByName(String name);
  
  List<BookCategoryView> loadBookCategoryOrderById(boolean before,boolean after,Integer lastKeyset);
  
}
