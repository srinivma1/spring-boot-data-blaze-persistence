/**
 * 
 */
package de.michlb.sample.view;

import java.util.List;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import de.michlb.sample.domain.BookCategory;


/**
 * @author mahes
 *
 */
@EntityView(BookCategory.class)
public interface BookCategoryView {

	  @IdMapping
	  public Integer getId();
	  
	  public String getName();
	  
	  public List<BookView> getBooks();
}
