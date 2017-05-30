package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.AnswersEntity;
import entities.CategoriesEntity;
import entities.QuestionsEntity;

/**
 * Class for users creating questions
 */

public class JpqlCommands {

	QuestionsEntity questionEntity = new QuestionsEntity();
	CategoriesEntity categoriesEntity = new CategoriesEntity();


	public List getCategoryNames(){
		List categoryNamesList = new ArrayList<>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		categoryNamesList = em.createNamedQuery("getAllCategoryNames").getResultList();
		return categoryNamesList;
	}

	public CategoriesEntity getCategory(){
		CategoriesEntity category;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		category = (CategoriesEntity) em.createNamedQuery("getCategory").setParameter("category", "Marvel").getSingleResult();
		return category;
	}


	public void create() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		questionEntity.setQuestion("Who is bruce wayne?");
		categoriesEntity.setCategory("DC comics");
		AnswersEntity answer1 = new AnswersEntity();
		answer1.setAnswer("Spiderman");
		answer1.setCorrect(false);
		questionEntity.addAnswer(answer1);

		AnswersEntity answer2 = new AnswersEntity();
		answer2.setCorrect(true);
		answer2.setAnswer("Batman");
		questionEntity.addAnswer(answer2);

		AnswersEntity answer3 = new AnswersEntity();
		answer3.setCorrect(false);
		answer3.setAnswer("Fatman");
		questionEntity.addAnswer(answer3);

		AnswersEntity answer4 = new AnswersEntity();
		answer4.setCorrect(false);
		answer4.setAnswer("WonderWoman");
		questionEntity.addAnswer(answer4);
		questionEntity.addCategories(categoriesEntity);
		categoriesEntity.addQuestion(questionEntity);
		em.persist(categoriesEntity);
		em.persist(answer1);
		em.persist(answer2);
		em.persist(answer3);
		em.persist(answer4);
		em.persist(questionEntity);
		em.getTransaction().commit();

	}



}
