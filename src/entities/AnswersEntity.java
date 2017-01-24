package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by robin on 2017-01-24.
 */
@Entity
public class AnswersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int answerID;
	private String answer;

	public AnswersEntity(int answerID, String answer) {
		this.answer = answer;
		this.answerID = answerID;
	}

	public AnswersEntity() {
	}

	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
