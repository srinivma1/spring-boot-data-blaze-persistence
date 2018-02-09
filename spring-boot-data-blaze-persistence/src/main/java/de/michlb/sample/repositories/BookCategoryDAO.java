/**
 * 
 */
package de.michlb.sample.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

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

}
