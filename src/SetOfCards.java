import java.util.ArrayList;
import java.util.List;

public class SetOfCards {
    private List<Card> cardsForGame = new ArrayList();

    public List<Card> getCardsForGame() {
        return cardsForGame;
    }

    public void setCardsForGame(List<Card> cardsForGame) {
        this.cardsForGame = cardsForGame;
    }

    public void addCard (Card card){
        cardsForGame.add(card);
    }

    public Integer getNumberOfCards(){
        return cardsForGame.size();
    }

    public void showCards(){
        for (Card card : cardsForGame){
            System.out.println("Letter to show: " + card.getLetter());
            System.out.println("Picture to show: " + card.getPicturePath());
            System.out.println("Sound to play: " + card.getPronunciationPath());
        }
    }
}