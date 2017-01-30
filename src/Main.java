import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	List<QuestionsEntity> questList = new ArrayList<>();

	public static void main(String[] args) {

		new Main().create();

	}

	public void create() {
		em.getTransaction().begin();

		CategoriesEntity categoriesEntity = (CategoriesEntity) em.createQuery("SELECT c FROM CategoriesEntity c where c.category ='Marvel'").getSingleResult();

		question.setQuestion("Black Panther");
//		category.setCategory("Marvel");
		AnswersEntity answer1 = new AnswersEntity();
		answer1.setAnswer("yea");
		answer1.setCorrect(false);
		question.addAnswer(answer1);

		AnswersEntity answer2 = new AnswersEntity();
		answer2.setCorrect(false);
		answer2.setAnswer("naw");
		question.addAnswer(answer2);

		AnswersEntity answer3 = new AnswersEntity();
		answer3.setCorrect(false);
		answer3.setAnswer("nope");
		question.addAnswer(answer3);

		AnswersEntity answer4 = new AnswersEntity();
		answer4.setCorrect(true);
		answer4.setAnswer("maybe");
		question.addAnswer(answer4);

		if(categoriesEntity != null){
			question.addCategories(categoriesEntity);
		}else{
			question.addCategories(category);
		}

		categoriesEntity.addQuestion(question);
		em.persist(answer1);
		em.persist(answer2);
		em.persist(answer3);
		em.persist(answer4);
		em.persist(question);
		em.getTransaction().commit();

	}

	public void read(){
		em.getTransaction().begin();
		Query getQuest = em.createQuery("SELECT c FROM CategoriesEntity c where c.category ='Philosophy'");

		CategoriesEntity categoryResult = (CategoriesEntity) getQuest.getSingleResult();

		for ( QuestionsEntity q : categoryResult.getQuestions()) {
			System.out.println(q.getQuestion());
			System.out.println(q.getAnswersList().get(2).getAnswer());
		}
		em.getTransaction().commit();
	}
}
