package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by robin on 2017-01-24.
 */
@Entity
public class QuestionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int questionsID;
	private String question;

	public QuestionsEntity() {
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

	public QuestionsEntity(int questionsID, String question) {

		this.questionsID = questionsID;
		this.question = question;
	}
}
