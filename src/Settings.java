import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    Properties properties = new Properties();
    private String difficulty;
    private int numberOfCards;

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        switch (difficulty) {
            case "easy" -> numberOfCards = 12; // how many cards that will be displayed
            case "medium" -> numberOfCards = 24; // how many cards that will be displayed
            case "hard" -> numberOfCards = 36; // how many cards that will be displayed
        }
        return numberOfCards;
    }

    public Settings() {
        try {
            properties.load(new FileInputStream("src/Setting.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        difficulty = properties.getProperty("difficulty", "easy");
    }
}
