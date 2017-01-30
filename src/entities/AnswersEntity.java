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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerID;
	private String answer;
	private boolean correct;

	public AnswersEntity(int answerID, String answer, boolean correct) {
		this.answer = answer;
		this.answerID = answerID;
		this.correct = correct;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
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
