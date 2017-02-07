package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
		@NamedQuery(query = "SELECT c.category FROM CategoriesEntity c ", name="getAllCategoryNames"),
		@NamedQuery(query = "SELECT c FROM CategoriesEntity c WHERE c.category = :category", name = "getCategory")})

public class CategoriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int categoryID;
	private String category;
	@ManyToMany
	private List<QuestionsEntity> questionsList;


	public CategoriesEntity(int categoryID, String category) {
		this.category = category;
		this.categoryID = categoryID;
		this.questionsList = new ArrayList<>();
	}

	public CategoriesEntity() {
		this.questionsList = new ArrayList<>();
	}

	public List<QuestionsEntity> getQuestions(){return questionsList;}

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
