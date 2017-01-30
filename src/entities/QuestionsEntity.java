package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class QuestionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int questionsID;
	private String question;
	@OneToMany
	private List<AnswersEntity> answersList;
	@ManyToMany
	private List<CategoriesEntity> categoriesList;

	public QuestionsEntity(int questionsID, String question) {
		this.questionsID = questionsID;
		this.question = question;
		this.answersList = new ArrayList<>();
		this.categoriesList = new ArrayList<>();
	}

	public QuestionsEntity() {
		this.answersList = new ArrayList<>();
		this.categoriesList = new ArrayList<>();
	}

	public void addAnswer(AnswersEntity answer) {
		answersList.add(answer);
	}

	public void addCategories(CategoriesEntity category) {
		categoriesList.add(category);
	}

	public int getQuestionsID() {
		return questionsID;
	}

	public void setQuestionsID(int questionsID) {
		this.questionsID = questionsID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswersEntity> getAnswersList() {
		return answersList;
	}

	public void setAnswersList(List<AnswersEntity> answersList) {
		this.answersList = answersList;
	}

	public List<CategoriesEntity> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<CategoriesEntity> categoriesList) {
		this.categoriesList = categoriesList;
	}

}
