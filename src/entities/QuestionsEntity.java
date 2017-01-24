package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Created by robin on 2017-01-24.
 */
@Entity
public class QuestionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

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
	}

	public void addAnswer(AnswersEntity answer) {
		answersList.add(answer);
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

}
