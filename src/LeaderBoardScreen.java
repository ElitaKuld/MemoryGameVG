import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoardScreen extends JFrame implements ActionListener {
    JButton returnToHomeScreenButton;
    JButton startNewGameButton;
    JPanel basePanel;
    List<String> listOfResults;

    public LeaderBoardScreen() {
        setUpLeaderboardScreen();
    }

    public void setUpLeaderboardScreen() {
        //Color backGroundColor = new Color(77,27,102);
        Color backGroundColor = new Color(255, 102, 102);

        setTitle("LeaderBoard");

        basePanel = new JPanel(new GridLayout(11, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        basePanel.setBackground(backGroundColor);
        buttonPanel.setBackground(backGroundColor);
        returnToHomeScreenButton = new JButton("Return to Homescreen");
        startNewGameButton = new JButton("Start Game");

        returnToHomeScreenButton.addActionListener(this);
        startNewGameButton.addActionListener(this);

        listOfResults = sortResults(readResultsFromAFile());

        add(basePanel);
        basePanel.add(buttonPanel);
        buttonPanel.add(returnToHomeScreenButton);
        buttonPanel.add(startNewGameButton);
        createLabels();

        setSize(800, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createLabels() {
        for (int i = 0; i < 10; i++) {
            if (i<listOfResults.size()) {
                JLabel label = new JLabel("Place " + i + ":       " + listOfResults.get(i) + " clicks");
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Times New Roman", Font.BOLD, 24));
                label.setBorder(new LineBorder(Color.WHITE, 2));
                basePanel.add(label);
            }
            else {
                JLabel label = new JLabel("Place " + i + ":");
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Times New Roman", Font.BOLD, 24));
                label.setBorder(new LineBorder(Color.WHITE, 2));
                basePanel.add(label);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startNewGameButton) {
            setVisible(false);
            GameScreen gameScreen = new GameScreen();
        } else if (e.getSource() == returnToHomeScreenButton) {
            setVisible(false);
            HomeScreen homeScreen = new HomeScreen();
        }
    }

    public List<String> readResultsFromAFile(){
        List<String> listOfResults = new ArrayList<>();
        String fileName = "Results.txt";

        File file = new File(fileName);
        if (file.length() == 0) { // är filen tom?
            return listOfResults;
        }

        try (BufferedReader buffRead = Files.newBufferedReader(Path.of(fileName))) {

            while (true) { // läs filen och kopiera till listan
                String result = buffRead.readLine();
                if (result == null)
                    break;
                listOfResults.add(result);
            }
        } catch (NoSuchFileException e) {
            JOptionPane.showMessageDialog(null, "Felmeddelande!\nIngen fil hittades.");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fel inträffade vid läsning från fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
        return listOfResults;
    }

    public List<String> sortResults (List<String> listOfResults){
        Collections.sort(listOfResults);
        return listOfResults;
    }
}