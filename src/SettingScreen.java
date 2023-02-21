import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SettingScreen extends JFrame {
    //Color backGroundColor = new Color(77, 27, 102);
    Color backGroundColor = new Color(255, 102, 102);

    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
    JButton returnToHomeScreenButton = new JButton("Return to HomeScreen");
    JPanel topPanel = new JPanel(new GridLayout(1,1));


    public SettingScreen() {
        setTitle("Choose Difficulty");

        returnToHomeScreenButton.setPreferredSize(new Dimension(500,45));

        JButton difficultyEasyButton = new JButton("Easy");
        difficultyEasyButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        difficultyEasyButton.setForeground(Color.WHITE);
        difficultyEasyButton.setBackground(backGroundColor);
        difficultyEasyButton.setBorder(new LineBorder(Color.WHITE, 2));

        JButton difficultyMediumButton = new JButton("Medium");
        difficultyMediumButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        difficultyMediumButton.setForeground(Color.WHITE);
        difficultyMediumButton.setBackground(backGroundColor);
        difficultyMediumButton.setBorder(new LineBorder(Color.WHITE, 2));

        JButton difficultyHardButton = new JButton("Hard");
        difficultyHardButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        difficultyHardButton.setForeground(Color.WHITE);
        difficultyHardButton.setBackground(backGroundColor);
        difficultyHardButton.setBorder(new LineBorder(Color.WHITE, 2));

        returnToHomeScreenButton.addActionListener(e -> {
            this.setVisible(false);
            HomeScreen homeScreen = new HomeScreen();
        });
        difficultyEasyButton.addActionListener(e -> {
            HomeScreen.settings.setDifficulty("easy");
            JOptionPane.showMessageDialog(null, "Difficulty set to easy");
        });
        difficultyMediumButton.addActionListener(e -> {
            HomeScreen.settings.setDifficulty("medium");
            JOptionPane.showMessageDialog(null, "Difficulty set to medium");
        });
        difficultyHardButton.addActionListener(e -> {
            HomeScreen.settings.setDifficulty("hard");
            JOptionPane.showMessageDialog(null, "Difficulty set to hard");
        });

        topPanel.add(returnToHomeScreenButton);

        buttonPanel.add(difficultyEasyButton, BorderLayout.NORTH);
        buttonPanel.add(difficultyMediumButton, BorderLayout.CENTER);
        buttonPanel.add(difficultyHardButton, BorderLayout.SOUTH);

        add(basePanel);
        basePanel.add(topPanel, BorderLayout.NORTH);
        basePanel.add(buttonPanel, BorderLayout.CENTER);

        this.setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}