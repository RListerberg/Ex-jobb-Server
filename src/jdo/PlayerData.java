package jdo;

import java.util.List;


public class PlayerData {
    public boolean isReady;

    public int questionsAnswered;
    public int questionsAnsweredCorrectly;

    public List<Boolean> categoriesAnsweredCorrectly;


    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
