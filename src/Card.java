import javax.swing.*;
import java.awt.*;

public class Card extends JLabel implements Flippable {

    private String letter;
    private String picturePath;
    private ImageIcon picture;
    private String pronunciationPath;
    private Color colorOfReverseSide = new Color(255, 102, 102);

    public Card() {
    }

    public Card(String letter, String picturePath, String pronunciationPath) {
        this.letter = letter;
        this.picturePath = picturePath;
        this.pronunciationPath = pronunciationPath;
        picture = getScaledImage();
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }

    public ImageIcon getPicture() {
        return picture;
    }

    public String getPronunciationPath() {
        return pronunciationPath;
    }

    public void setPronunciationPath(String pronunciationPath) {
        this.pronunciationPath = pronunciationPath;
    }

    public Color getColorOfReverseSide() {
        return colorOfReverseSide;
    }

    @Override
    public void flipACardUp() {
        setIcon(picture);
    }

    public void flipACardDown() {
        setIcon(null);
    }

    public ImageIcon getScaledImage() {
        ImageIcon icon = new ImageIcon(this.picturePath);
        Image image = icon.getImage();
        Image imageScaled = image.getScaledInstance(165, 210, Image.SCALE_SMOOTH);
        return new ImageIcon(imageScaled);
    }
}