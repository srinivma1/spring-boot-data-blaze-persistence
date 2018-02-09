/**
 * 
 */
package de.michlb.sample.domain;

import java.io.Serializable;

/**
 * @author mahes
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Book implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private BookCategory bookCategory;
    private Integer bookCategoryId;
    
   //For manytoone only persist the foreign key id ..so created another attribute..declared as @Transient so that it is not persisted but
    //serializable to JSON
    @Transient
    public Integer getBookCategoryId() {
		return bookCategory.getId();
	}

	public void setBookCategoryId(Integer bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    //I do not need this object only ID hence marked as @JSONIgnore
   @JsonIgnore
  
    @ManyToOne
    @JoinColumn(name = "book_category_id")
    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
