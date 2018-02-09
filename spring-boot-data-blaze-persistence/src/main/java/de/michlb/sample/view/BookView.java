/**
 * 
 */
package de.michlb.sample.view;

import com.blazebit.persistence.view.EntityView;

import de.michlb.sample.domain.Book;

/**
 * @author mahes
 *
 */
@EntityView(Book.class)
public interface BookView {
	
	public String getName();

}
