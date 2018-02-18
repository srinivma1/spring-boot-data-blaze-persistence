/**
 * 
 */
package de.michlb.sample.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import de.michlb.sample.domain.BookCategory;
import de.michlb.sample.view.BookCategoryView;

/**
 * @author mahes
 *
 */
@Component
public class BookCategoryDAO {
	
   @Autowired
   private EntityManager entityManager;
	
	@Autowired
	private CriteriaBuilderFactory criteriaBuilderFactory;
	
	@Autowired
	private EntityViewManager entityViewManager;

	public List<BookCategoryView> getAllBookCategories(){
		
		CriteriaBuilder<BookCategory> cb = criteriaBuilderFactory.create(entityManager, BookCategory.class);
		cb.from(BookCategory.class, "bookCategory");

		EntityViewSetting<BookCategoryView, CriteriaBuilder<BookCategoryView>> setting = EntityViewSetting.create(BookCategoryView.class);
		List<BookCategoryView> list = entityViewManager
		                        .applySetting(setting, cb)
		                        .getResultList();	
		
		return list;
	}
	
	public List<BookCategoryView> getBookCategoriesOrderById(boolean before,boolean after,Integer lastKeyset){
		CriteriaBuilder<BookCategory> cb = criteriaBuilderFactory.create(entityManager, BookCategory.class,"bookCategory").orderByAsc("bookCategory.id");
	

		EntityViewSetting<BookCategoryView, CriteriaBuilder<BookCategoryView>> setting = EntityViewSetting.create(BookCategoryView.class);
		List<BookCategoryView> bookCategoryList = null;
		if(lastKeyset!=null) {
			if(before)
				bookCategoryList = entityViewManager
                        .applySetting(setting, cb).beforeKeyset(lastKeyset).setMaxResults(20).getResultList();
			else if(after)
				bookCategoryList = entityViewManager
	            .applySetting(setting, cb).afterKeyset(lastKeyset).setMaxResults(20).getResultList();
		} else {
			bookCategoryList = entityViewManager
                    .applySetting(setting, cb).setMaxResults(20).getResultList();
		}
		return bookCategoryList;
		
	}

}
