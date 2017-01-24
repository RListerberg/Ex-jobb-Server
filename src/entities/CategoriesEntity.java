package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by robin on 2017-01-24.
 */
@Entity
public class CategoriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int categoryID;
	private String category;
	@OneToMany
	private List<QuestionsEntity> questionsList;

	public CategoriesEntity(int categoryID, String category) {
		this.category = category;
		this.categoryID = categoryID;
		this.questionsList = new ArrayList<>();
	}

	public CategoriesEntity() {
	}

	public void addQuestion(QuestionsEntity question) {
		questionsList.add(question);
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
