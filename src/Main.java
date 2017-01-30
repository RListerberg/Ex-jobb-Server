import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by robin on 2017-01-27.
 */
public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("NewPersistenceUnit");
		EntityManager em = factory.createEntityManager();


	}

}
