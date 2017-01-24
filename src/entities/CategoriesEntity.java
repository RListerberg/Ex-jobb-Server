package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by robin on 2017-01-24.
 */
@Entity
public class CategoriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int categoryID;
	private String category;

	public CategoriesEntity(int categoryID, String category) {
		this.category = category;
		this.categoryID = categoryID;
	}

	public CategoriesEntity() {
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
