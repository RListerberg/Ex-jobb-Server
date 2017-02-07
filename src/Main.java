import java.io.IOException;


import entities.CategoriesEntity;
import jpa.JpqlCommands;

/**
 * Created by robin on 2017-01-27.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		JpqlCommands commander = new JpqlCommands();
		CategoriesEntity categoriesEntity = new CategoriesEntity();
		System.out.println(commander.getCategory().getQuestions().get(0).getAnswersList().get(0).getAnswer());
	}

}
