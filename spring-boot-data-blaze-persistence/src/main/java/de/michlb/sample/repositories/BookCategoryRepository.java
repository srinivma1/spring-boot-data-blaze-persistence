/**
 * 
 */
package de.michlb.sample.repositories;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.michlb.sample.domain.BookCategory;
import de.michlb.sample.view.BookCategoryView;

/**
 * @author mahes
 *
 */

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

	public void deleteByIdIn(List<Integer> ids);
	
	@Query("select category from BookCategory category LEFT JOIN FETCH category.books book where category.name=:name")
	public Set<BookCategory> findByBookCategoryName(@Param("name") String name);
	


}
