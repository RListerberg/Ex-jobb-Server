import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.AnswersEntity;
import entities.CategoriesEntity;
import entities.QuestionsEntity;

/**
 * Created by robin on 2017-01-27.
 */
public class Main {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
	EntityManager em = factory.createEntityManager();
	QuestionsEntity question = new QuestionsEntity();
	CategoriesEntity category = new CategoriesEntity();

	public static void main(String[] args) {

		new Main().create();

	}

	public void create() {
		em.getTransaction().begin();
		question.setQuestion("Who is bruce wayne?");
		category.setCategory("DC comics");
		AnswersEntity answer1 = new AnswersEntity();
		answer1.setAnswer("Spiderman");
		answer1.setCorrect(false);
		question.addAnswer(answer1);

		AnswersEntity answer2 = new AnswersEntity();
		answer2.setCorrect(true);
		answer2.setAnswer("Batman");
		question.addAnswer(answer2);

		AnswersEntity answer3 = new AnswersEntity();
		answer3.setCorrect(false);
		answer3.setAnswer("Fatman");
		question.addAnswer(answer3);

		AnswersEntity answer4 = new AnswersEntity();
		answer4.setCorrect(false);
		answer4.setAnswer("WonderWoman");
		question.addAnswer(answer4);
		question.addCategories(category);
		category.addQuestion(question);
		em.persist(category);
		em.persist(answer1);
		em.persist(answer2);
		em.persist(answer3);
		em.persist(answer4);
		em.persist(question);
		em.getTransaction().commit();

	}

}
