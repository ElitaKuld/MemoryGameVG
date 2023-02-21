import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameScreen extends JFrame implements ActionListener {

    JPanel basePanel;
    JPanel topPanel;
    JPanel cardPanel;
    JButton newGameButton;
    JButton shuffleButton;
    Pronunciation pronunciation = new Pronunciation();
    SetBuilder cardBuilder = new SetBuilder();

    private Timer timer;

    private int numberOfCorrectPairs = 0;
    private int numberOfClicks = 0;
    Card cardToCompareA;
    Card cardToCompareB;
    private List<Integer> duplicates = new ArrayList<>();
    private int randomIndex;
    SetOfCards setOfOriginalCards;
    List<Card> listOfCardsToShow;
    //List<Card> listOfCards;

    public GameScreen() {
        setUpGameScreen();
    }

    public void setUpGameScreen() {

        basePanel = new JPanel(new BorderLayout());

        add(basePanel);

        topPanel = new JPanel(new GridLayout(1, 2));
        cardPanel = new JPanel(new GridLayout(4, HomeScreen.settings.getDifficulty() / 4)); //
        shuffleButton = new JButton("Shuffle");
        newGameButton = new JButton("Start new game");

        basePanel.add(cardPanel, BorderLayout.CENTER);
        basePanel.add(topPanel, BorderLayout.NORTH);
        topPanel.add(newGameButton);
        topPanel.add(shuffleButton);

        setOfOriginalCards = cardBuilder.createSetOfCards();
        listOfCardsToShow = createListOfCardsToShow(setOfOriginalCards);

        for (Card card : listOfCardsToShow) {
            card.setOpaque(true);
            card.setBackground(card.getColorOfReverseSide());
            card.setBorder(new LineBorder(Color.WHITE, 3));
            card.setPreferredSize(new Dimension(175, 225));
            card.addMouseListener(mouseListener);
            cardPanel.add(card);
        }

        newGameButton.addActionListener(this);
        shuffleButton.addActionListener(this);

        timer = new Timer(2000, actionEvent -> twoCardsUp(cardToCompareA, cardToCompareB)); // 2000 = 2 sekunder

        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newGameButton) {
            numberOfCorrectPairs = 0; // viktigt om man väljer att starta på nytt spel mitt i nuvarande spelet
            numberOfClicks = 0;
            cardPanel.removeAll();
            listOfCardsToShow = createListOfCardsToShow(setOfOriginalCards);

            for (Card card : listOfCardsToShow) {
                card.setOpaque(true);
                card.setBackground(card.getColorOfReverseSide());
                card.setBorder(new LineBorder(Color.WHITE, 3));
                card.setPreferredSize(new Dimension(175, 225));
                card.addMouseListener(mouseListener);
                cardPanel.add(card);
            }
            cardPanel.validate();
            cardPanel.repaint();
        }

        if (e.getSource() == shuffleButton) {
            cardPanel.removeAll();
            Collections.shuffle(listOfCardsToShow);
            for (Card card : listOfCardsToShow) {
                cardPanel.add(card);
            }
            cardPanel.validate();
            cardPanel.repaint();
        }
    }

    MouseAdapter mouseListener = new MouseAdapter() {

        int numberCardsUp = 0;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (timer.isRunning()) return;

            if (e.getSource() instanceof JLabel && numberCardsUp == 0) {
                ((Card) e.getSource()).flipACardUp();
                cardToCompareA = (Card) e.getSource();
                numberCardsUp = 1;
                numberOfClicks++;
                System.out.println("I clicked once");
            } else if (e.getSource() instanceof JLabel && numberCardsUp == 1) {
                ((Card) e.getSource()).flipACardUp();
                cardToCompareB = (Card) e.getSource();
                numberCardsUp = 2;
                numberOfClicks++;
                System.out.println("I clicked twice");
            }
            if (cardToCompareA != null && cardToCompareB != null && numberCardsUp == 2) {
                timer.start();
                numberCardsUp = 0;
            }
        }
    };


    public List<Card> createListOfCardsToShow(SetOfCards originalSetOfCards) { // original kortleken består av 29 kort
        int cardsToMake = HomeScreen.settings.getDifficulty();
        List<Card> listOfCardsToShow = new LinkedList<>();
        duplicates.clear();

        for (int i = 0; i < cardsToMake / 2; i++) { // viktigt att dela cardsToMake med två för att vi skapar par av kort
            while (true) {
                randomIndex = (int) (Math.random() * 28) + 1; // 26 för att vi har 26 olika bokstäver i vår kortlek
                if (!duplicates.contains(randomIndex)) {
                    break;
                }
            }
            duplicates.add(randomIndex);
            Card card = originalSetOfCards.getCardsForGame().get(randomIndex);
            Card theFirstCard = new Card();
            theFirstCard.setLetter(card.getLetter());
            theFirstCard.setPicturePath(card.getPicturePath());
            theFirstCard.setPronunciationPath(card.getPronunciationPath());
            theFirstCard.setPicture(card.getPicture());
            Card theSecondCard = new Card();
            theSecondCard.setLetter(card.getLetter());
            theSecondCard.setPicturePath(card.getPicturePath());
            theSecondCard.setPronunciationPath(card.getPronunciationPath());
            theSecondCard.setPicture(card.getPicture());
            listOfCardsToShow.add(theFirstCard);
            listOfCardsToShow.add(theSecondCard);
        }
        Collections.shuffle(listOfCardsToShow);
        return listOfCardsToShow;
    }

    private void twoCardsUp(Card cardToCompareA, Card cardToCompareB) {
        checkIfSameCards(cardToCompareA, cardToCompareB);
        timer.stop();
        revalidate();
    }

    public boolean areSameCards(Card cardA, Card cardB) {
        if (cardA.getLetter().equals(cardB.getLetter()) && cardA != cardB) {
            System.out.println("Same cards");
            return true;
        } else {
            System.out.println("Different cards");
            return false;
        }
    }

    public void checkIfSameCards(Card cardA, Card cardB) {
        if (areSameCards(cardA, cardB)) {
            pronunciation.setFile(cardA.getPronunciationPath()); // tanken är att ordet uttalas när användaren hittar båda 2 korten
            pronunciation.play();
            cardA.removeMouseListener(mouseListener);
            cardB.removeMouseListener(mouseListener);
            numberOfCorrectPairs++;
            System.out.println("Antal korrekta par: " + numberOfCorrectPairs); // kontroll
        } else if (!areSameCards(cardA, cardB)) {
            cardA.flipACardDown();
            cardB.flipACardDown();
        }

        // kontrollera om spelaren vann
        if (numberOfCorrectPairs == HomeScreen.settings.getDifficulty() / 2) { // antal brickor delat med 2
            saveResultToAList(numberOfClicks);
            JOptionPane.showMessageDialog(null, "Congratulations! You won the game in " +
                    numberOfClicks + " clicks!");
        }
    }

    public void saveResultToAList(int numberOfClicks) {
        String fileName = "Results.txt";
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            pw.write(numberOfClicks + "\n");
        } catch (IOException e) {
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
        }
    }
}